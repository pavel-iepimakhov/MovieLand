package com.movieland.controller;

import com.google.gson.reflect.TypeToken;
import com.movieland.dao.MovieReviewDao;
import com.movieland.entity.Movie;
import com.movieland.entity.User;
import com.movieland.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonConverterService jsonConverterService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private MovieReviewService movieReviewService;

    @Autowired
    private UserService userService;

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

    @RequestMapping(value = "/v1/auth", consumes = "application/json;charset=UTF-8", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getSecurityToken(@RequestBody String body) {
        Map<String,String> userPassMap = jsonConverterService.getStringMapFromJson(body);
        String token = securityService.getSecurityToken(userPassMap.get("username"), userPassMap.get("password"));
        if(token != null) {
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/v1/review", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<String> addMovieReview(@RequestBody String body) {
        Map<String,String> request = jsonConverterService.getStringMapFromJson(body);
        User user  = securityService.getUserByToken(request.get("token"));
        if(user != null && user.getUserRole().equals("user") || user.getUserRole().equals("admin")) {
            movieReviewService.addReview(Integer.parseInt(request.get("movieid")), user.getUserId(), request.get("reviewtext"));
            return  new ResponseEntity<String>(HttpStatus.OK);
        }
        else return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/v1/review", consumes = "application/json", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> removeMoviewReview(@RequestBody String body) {
        Map<String,String> request = jsonConverterService.getStringMapFromJson(body);
        User user  = securityService.getUserByToken(request.get("token"));
        if(user != null && user.getUserRole().equals("user") || user.getUserRole().equals("admin")) {
            movieReviewService.removeMovieReview(Integer.parseInt(request.get("movieid")), user.getUserId());
        }
        return null;
    }

    //// TODO: 16.06.2016 make separate controllers
}
