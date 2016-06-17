package com.movieland.controller;

import com.movieland.entity.Movie;
import com.movieland.service.*;
import com.movieland.util.JsonConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class MovieController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
    long startTime;

    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonConverterService jsonConverterService;

    @RequestMapping(value = "/v1/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllMovies(){
        if(LOGGER.isDebugEnabled()) {
            startTime = System.nanoTime();
            LOGGER.debug("getAllMovies started execution");
        }

        List<Movie> movies = movieService.getAllMovies();

        if(LOGGER.isDebugEnabled()) {
            startTime = System.nanoTime();
            LOGGER.debug("getAllMovies finished execution. Elapsed time is {}",System.nanoTime() - startTime);
        }
        return jsonConverterService.objectToJson(movies);
    }

    @RequestMapping(value = "/v1/movie/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable int movieId){
        Movie movie = movieService.getMovieById(movieId);
        return jsonConverterService.objectToJson(movie);
    }

}
