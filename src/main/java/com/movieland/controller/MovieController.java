package com.movieland.controller;

import com.movieland.entity.Movie;
import com.movieland.entity.Poster;
import com.movieland.entity.User;
import com.movieland.entity.dto.MovieWithUserRatingDTO;
import com.movieland.entity.request.MergeMovieRatingRequest;
import com.movieland.security.SecurityService;
import com.movieland.service.*;
import com.movieland.util.CurrencyEnum;
import com.movieland.util.CurrencyExchangeRateService;
import com.movieland.util.ExchangeRate;
import com.movieland.util.JsonConverterService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
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
    private ModelMapper modelMapper;

    @Autowired
    private CurrencyExchangeRateService currencyExchangeRateService;

    @Autowired
    private MoviePosterService moviePosterService;

    @RequestMapping(value = "/v1/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllMovies(@RequestParam(required = false) CurrencyEnum currency) {
        LOGGER.info("Method getAllMovies was invoked" + (currency != null ? ". Currency parameter is " + currency : null));
        List<Movie> movies = movieService.getAllMovies();

        //perform currency convertion in case USD or EUR currency request parameter has been specified.
        if (currency != null && currency != CurrencyEnum.UAH) {
            ExchangeRate exchangeRate = currencyExchangeRateService.getCurrencyExchangeRate(currency);
            float rate = exchangeRate.getRate();
            movies.forEach(movie -> movie.setMoviePrice(new BigDecimal(Double.toString(movie.getMoviePrice() / rate)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
        }
        return jsonConverterService.objectToJson(movies);
    }

    @RequestMapping(value = "/v1/movie/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable int movieId,
                               @RequestHeader(value = "Security-Token", required = false) String securityToken,
                               @RequestParam(required = false) CurrencyEnum currency) {
        LOGGER.info("User called getMovieById for movie {}", movieId);
        Float userRating;
        Movie movie = movieService.getMovieById(movieId);

        //perform currency convertion in case USD or EUR currency request parameter has been specified.
        if (currency != null && currency != CurrencyEnum.UAH) {
            ExchangeRate exchangeRate = currencyExchangeRateService.getCurrencyExchangeRate(currency);
            float rate = exchangeRate.getRate();
            movie.setMoviePrice(new BigDecimal(Double.toString(movie.getMoviePrice() / rate)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }

        Object result = movie;
        if (securityToken != null) {
            User user = securityService.getUserByToken(securityToken);
            if (user != null) {
                int userId = user.getUserId();
                userRating = movieRatingService.getUserMovieRating(movieId, userId);
                if (userRating != null) {
                    MovieWithUserRatingDTO movieWithUserRatingDTO = modelMapper.map(result, MovieWithUserRatingDTO.class);
                    movieWithUserRatingDTO.setUserRating(userRating);
                    result = movieWithUserRatingDTO;
                }
            }
        }
        return jsonConverterService.objectToJson(result);
    }

    @RequestMapping(value = "/v1/movie/rate", consumes = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public void mergeUserMovieRating(@RequestHeader(value = "Security-Token") String securityToken, @RequestBody String body) {
        MergeMovieRatingRequest request = jsonConverterService.jsonToObject(body, MergeMovieRatingRequest.class);
        int movieId = request.getMovieId();
        float rating = request.getRating();
        User user = securityService.getUserByToken(securityToken);
        if (user != null && (user.isUser() || user.isAdmin())) {
            int userId = user.getUserId();
            LOGGER.info("User {} called mergeUserMovieRating method to add/change rating {} for movie {}", userId, rating, movieId);
            movieRatingService.mergeUserMovieRating(movieId, userId, rating);
        }
    }

    @RequestMapping(value = "/v1/poster/{movieId}", method = RequestMethod.GET, produces = "image/jpeg")
    @ResponseBody
    public byte[] getMoviePoster(@PathVariable int movieId) {
        Poster poster = moviePosterService.getMoviePoster(movieId);
        return poster.getPosterImage();
    }


}
