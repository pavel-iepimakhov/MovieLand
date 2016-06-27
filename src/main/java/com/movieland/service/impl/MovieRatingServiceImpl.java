package com.movieland.service.impl;

import com.movieland.dao.MovieRatingDao;
import com.movieland.service.MovieRatingService;
import com.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class MovieRatingServiceImpl implements MovieRatingService {

    @Autowired
    private MovieRatingDao movieRatingDao;

    @Autowired
    private MovieService movieService;

    @Override
    public void mergeUserMovieRating(int movieId, int userId, float rating) {
        movieRatingDao.mergeUserMovieRating(movieId, userId, rating);
    }

    @Override
    public Float getUserMovieRating(int movieId, int userId) {
        return movieRatingDao.getUserMovieRating(movieId, userId);
    }
}
