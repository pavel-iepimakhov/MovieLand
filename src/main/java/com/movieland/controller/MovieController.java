package com.movieland.controller;

import com.movieland.entity.Movie;
import com.movieland.entity.Poster;
import com.movieland.entity.User;
import com.movieland.entity.dto.MovieWithUserRatingDTO;
import com.movieland.security.SecurityService;
import com.movieland.service.*;
import com.movieland.util.CurrencyEnum;
import com.movieland.util.CurrencyExchangeRateService;
import com.movieland.util.ExchangeRate;
import com.movieland.util.JsonConverterService;
import org.apache.commons.math3.util.Precision;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonConverterService jsonConverterService;

    @Autowired
    private MovieRatingService movieRatingService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CurrencyExchangeRateService currencyExchangeRateService;

    @Autowired
    private MoviePosterService moviePosterService;

    @RequestMapping(value = "/v1/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllMovies(@RequestParam(required = false) CurrencyEnum currency){
        LOGGER.info("Method getAllMovies was invoked" + currency != null ? ". Currency parameter is " + currency : null);
        List<Movie> movies = movieService.getAllMovies();

        //perform currency convertion in case USD or EUR currency request parameter has been specified.
        if(currency != null && currency != CurrencyEnum.UAH ) {
            ExchangeRate exchangeRate = currencyExchangeRateService.getCurrencyExchangeRate(currency);
            float rate = exchangeRate.getRate();
            movies.forEach((movie) -> movie.setMoviePrice(Precision.round(movie.getMoviePrice() / rate, 2)));
        }

        return jsonConverterService.objectToJson(movies);
    }

    @RequestMapping(value = "/v1/movie/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable int movieId, @RequestHeader(value="Security-Token", required = false) String securityToken){
        LOGGER.info("User called getMovieById for movie {}", movieId);
        Float userRating;
        Object result = movieService.getMovieById(movieId);
        if(securityToken != null) {
            User user  = securityService.getUserByToken(securityToken);
            if(user != null) {
                int userId = user.getUserId();
                userRating = movieRatingService.getUserMovieRating(movieId, userId);
                if(userRating != null) {
                    MovieWithUserRatingDTO movieWithUserRatingDTO = modelMapper.map(result, MovieWithUserRatingDTO.class);
                    movieWithUserRatingDTO.setUserRating(userRating);
                    result = movieWithUserRatingDTO;
                }
            }
        }
        return jsonConverterService.objectToJson(result);
    }

    @RequestMapping(value = "/v1/movie/rate", consumes = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String mergeUserMovieRating(@RequestHeader(value="Security-Token") String securityToken, @RequestBody String body){
        Map<String,String> request = jsonConverterService.getStringMapFromJson(body);
        int movieId = Integer.parseInt(request.get("movieid"));
        float rating = Float.parseFloat(request.get("rating"));
        User user  = securityService.getUserByToken(securityToken);
        if(user != null && (user.isUser() || user.isAdmin())) {
            int userId = user.getUserId();
            LOGGER.info("User {} called mergeUserMovieRating method to add/change rating {} for movie {}", userId, rating, movieId);
            movieRatingService.mergeUserMovieRating(movieId, userId, rating);
            threadPoolTaskExecutor.execute(() -> movieService.updateAverageMovieRating(movieId));
        }
        return null;
    }

//    @RequestMapping(value = "/v1/poster/{movieId}", method = RequestMethod.GET, produces = "image/jpeg")
//    @ResponseBody
//    public byte[] getMoviePoster(@PathVariable int movieId) {
//        Poster poster = moviePosterService.getMoviePoster(movieId);
//        return poster.getPosterImage();
//    }

    @RequestMapping(value = "/v1/poster/{movieId}", method = RequestMethod.GET, produces = "image/jpeg")
    public void getMoviePoster(@PathVariable int movieId, HttpServletResponse httpServletResponse) throws IOException {
        Poster poster = moviePosterService.getMoviePoster(movieId);
        httpServletResponse.setContentType("image/jpeg");
        httpServletResponse.setContentLength(poster.getPosterImage().length);
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(poster.getPosterImage());
        responseOutputStream.flush();
        responseOutputStream.close();
    }

}
