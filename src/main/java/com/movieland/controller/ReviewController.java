package com.movieland.controller;

import com.movieland.entity.User;
import com.movieland.util.JsonConverterService;
import com.movieland.service.MovieReviewService;
import com.movieland.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ReviewController {
    @Autowired
    private JsonConverterService jsonConverterService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private MovieReviewService movieReviewService;

    @RequestMapping(value = "/v1/review", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<String> addMovieReview(@RequestBody String body) {
        Map<String,String> request = jsonConverterService.getStringMapFromJson(body);
        User user  = securityService.getUserByToken(request.get("token"));
        if(user != null && (user.isUser() || user.isAdmin())) {
            movieReviewService.addReview(Integer.parseInt(request.get("movieid")), user.getUserId(), request.get("reviewtext"));
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/v1/review", consumes = "application/json", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> removeMovieReview(@RequestBody String body) {
        Map<String,String> request = jsonConverterService.getStringMapFromJson(body);
        User user  = securityService.getUserByToken(request.get("token"));
        if(user != null && (user.isUser() || user.isAdmin())) {
            movieReviewService.removeMovieReview(Integer.parseInt(request.get("reviewid")));
        }
        return null;
    }

}
