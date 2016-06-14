package com.movieland.controller;

import com.movieland.entity.Movie;
import com.movieland.service.MovieService;
import com.movieland.service.JsonConverterService;
import com.movieland.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonConverterService jsonConverterService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/v1/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return jsonConverterService.objectToJson(movies);
    }

    @RequestMapping(value = "/v1/movie/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable int movieId){
        Movie movie = movieService.getMovieById(movieId);
        return jsonConverterService.objectToJson(movie);
    }

    @RequestMapping(value = "/v1/auth", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getSecurityToken(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword){
        String token = securityService.getSecurityToken(userName, userPassword);
        if(token != null) {
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
