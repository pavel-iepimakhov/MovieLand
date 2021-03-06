package com.movieland.controller;

import com.movieland.entity.MovieReview;
import com.movieland.entity.User;
import com.movieland.entity.request.AddMovieReviewRequest;
import com.movieland.entity.request.RemoveMovieReviewRequest;
import com.movieland.util.JsonConverterService;
import com.movieland.service.MovieReviewService;
import com.movieland.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ReviewController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private JsonConverterService jsonConverterService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private MovieReviewService movieReviewService;

    @RequestMapping(value = "/v1/review", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<String> addMovieReview(@RequestBody String body, @RequestHeader(value="Security-Token") String securityToken) {
        AddMovieReviewRequest request = jsonConverterService.jsonToObject(body, AddMovieReviewRequest.class);
        User user  = securityService.getUserByToken(securityToken);
        if(user != null && (user.isUser() || user.isAdmin())) {
            MDC.put("userName", user.getUserName());
            int movieId = request.getMovieId();
            String reviewText = request.getReviewText();
            LOGGER.info("addMovieReview movieId = {}, reviewText = {}", movieId, reviewText);
            movieReviewService.addReview(movieId, user.getUserId(), reviewText);
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/v1/review", consumes = "application/json", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> removeMovieReview(@RequestBody String body, @RequestHeader(value="Security-Token") String securityToken) {
        RemoveMovieReviewRequest request = jsonConverterService.jsonToObject(body, RemoveMovieReviewRequest.class);
        User user  = securityService.getUserByToken(securityToken);
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        if(user != null) {
            MDC.put("userName", user.getUserName());
            int reviewId = request.getReviewId();
            LOGGER.info("Method removeMovieReview was invoked. reviewId = {}, userName = {}",reviewId, user.getUserName());
            movieReviewService.removeMovieReview(reviewId, user);
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(httpStatus);
    }

}
