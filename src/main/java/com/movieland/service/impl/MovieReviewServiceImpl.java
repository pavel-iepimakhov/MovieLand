package com.movieland.service.impl;

import com.movieland.dao.MovieReviewDao;
import com.movieland.entity.MovieReview;
import com.movieland.entity.User;
import com.movieland.service.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieReviewServiceImpl implements MovieReviewService {

    @Autowired
    MovieReviewDao movieReviewDao;

    @Override
    public void addReview(int movieId, int userId, String reviewText) {
        movieReviewDao.addReview(movieId, userId, reviewText);
    }

    @Override
    public List<MovieReview> getReviewsByMovieId(int movieId) {
        return movieReviewDao.getReviewsByMovieId(movieId);
    }

    @Override
    public void removeMovieReview(int reviewId, User callingUser) {
        MovieReview movieReview = getMovieReviewById(reviewId);
        //user can only delete reviews he owns, admin can delete any review
        if(callingUser.isAdmin() || (callingUser.isUser() && movieReview.getUser().getUserId() == callingUser.getUserId())) {
            movieReviewDao.removeMovieReview(reviewId);
        }
    }

    @Override
    public MovieReview getMovieReviewById(int reviewId) {
        return movieReviewDao.getMovieReviewById(reviewId);
    }
}
