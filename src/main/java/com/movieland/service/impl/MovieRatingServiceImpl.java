package com.movieland.service.impl;

import com.movieland.dao.MovieRatingDao;
import com.movieland.service.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieRatingServiceImpl implements MovieRatingService {

    @Autowired
    private MovieRatingDao movieRatingDao;

    @Override
    public void mergeUserMovieRating(int movieId, int userId, float rating) {
        movieRatingDao.mergeUserMovieRating(movieId, userId, rating);
    }
}
