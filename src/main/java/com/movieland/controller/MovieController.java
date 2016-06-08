package com.movieland.controller;

import com.movieland.entity.Movie;
import com.movieland.service.MovieService;
import com.movieland.service.JsonConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonConverterService jsonConverterService;

    @RequestMapping(value = "/v1/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return jsonConverterService.objectToJson(movies);
    }
}
