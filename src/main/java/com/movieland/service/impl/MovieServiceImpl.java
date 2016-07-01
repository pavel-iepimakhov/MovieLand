package com.movieland.service.impl;

import com.movieland.dao.MovieDao;
import com.movieland.entity.Genre;
import com.movieland.entity.Movie;
import com.movieland.entity.MovieReview;
import com.movieland.cache.CacheService;
import com.movieland.service.MovieReviewService;
import com.movieland.service.MovieService;
import com.movieland.util.CurrencyExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private MovieReviewService movieReviewService;

    @Autowired
    private CurrencyExchangeRateService currencyExchangeRateService;

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    @Override
    public Movie getMovieById(int movieId)
    {
        Movie movie = movieDao.getMovieById(movieId);
        List<MovieReview> reviews = movieReviewService.getReviewsByMovieId(movieId);
        movie.setReviews(reviews);
        List<Genre> genres = cacheService.getMovieGenres(movieId);
        movie.setGenres(genres);
        return movie;
    }

    @Override
    public List<Genre> getGenresByMovieId(int movieId) {
        return movieDao.getGenresByMovieId(movieId);
    }

    @Override
    public void updateAverageMovieRating(int movieId) {
        movieDao.updateAverageMovieRating(movieId);
    }

}
