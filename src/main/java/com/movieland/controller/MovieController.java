package com.movieland.controller;

import com.google.gson.Gson;
import com.movieland.entity.Movie;
import com.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    private Gson gson = new Gson();

    @RequestMapping(value = "/v1/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        String json = gson.toJson(movies);
        return json;
    }
}