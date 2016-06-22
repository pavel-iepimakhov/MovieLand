package com.movieland.controller;

import com.movieland.entity.Movie;
import com.movieland.entity.User;
import com.movieland.security.SecurityService;
import com.movieland.service.*;
import com.movieland.util.JsonConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

    @RequestMapping(value = "/v1/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllMovies(){
        LOGGER.info("Method getAllMovies was invoked");
        long startTime = 0;
        if(LOGGER.isDebugEnabled()) {
            startTime = System.nanoTime();
            LOGGER.debug("getAllMovies started execution");
        }

        List<Movie> movies = movieService.getAllMovies();

        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("getAllMovies finished execution. Elapsed time is {}", System.nanoTime() - startTime);
        }
        return jsonConverterService.objectToJson(movies);
    }

    @RequestMapping(value = "/v1/movie/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable int movieId, @RequestHeader(value="Security-Token", required = false) String securityToken){
        LOGGER.info("User called getMovieById for movie {}", movieId);
        Integer userId = null;
        if(securityToken != null) {
            User user  = securityService.getUserByToken(securityToken);
            if(user != null) {
                userId = user.getUserId();
            }
        }
        Movie movie = movieService.getMovieById(movieId, userId);
        return jsonConverterService.objectToJson(movie);
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
        }
        return null;
    }

}
