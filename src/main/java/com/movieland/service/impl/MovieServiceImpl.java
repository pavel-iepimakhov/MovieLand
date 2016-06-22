package com.movieland.service.impl;

import com.movieland.dao.MovieDao;
import com.movieland.entity.Genre;
import com.movieland.entity.Movie;
import com.movieland.entity.MovieReview;
import com.movieland.cache.MovieGenresCacheService;
import com.movieland.service.MovieReviewService;
import com.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private MovieGenresCacheService movieGenresCacheService;

    @Autowired
    private MovieReviewService movieReviewService;

    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public Movie getMovieById(int movieId, Integer userId)
    {
        Movie movie = movieDao.getMovieById(movieId, userId);
        List<MovieReview> reviews = movieReviewService.getReviewsByMovieId(movieId);
        movie.setReviews(reviews);
        List<Genre> genres = movieGenresCacheService.getMovieGenres(movieId);
        movie.setGenres(genres);
        return movie;
    }

    public List<Genre> getGenresByMovieId(int movieId) {
        return movieDao.getGenresByMovieId(movieId);
    }

}
