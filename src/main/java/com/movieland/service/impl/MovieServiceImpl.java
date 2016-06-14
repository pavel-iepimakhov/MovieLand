package com.movieland.service.impl;

import com.movieland.dao.MovieDao;
import com.movieland.entity.Genre;
import com.movieland.entity.Movie;
import com.movieland.entity.MovieReview;
import com.movieland.service.MovieGenresCacheService;
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

    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public Movie getMovieById(int movieId)
    {
        Movie movie = movieDao.getMovieById(movieId);
        List<MovieReview> reviews = movieDao.getReviewsByMovieId(movieId);
        movie.setReviews(reviews);
        List<Genre> genres = movieGenresCacheService.getMovieGenres(movieId);
        movie.setGenres(genres);
        return movie;
    }

    public List<Genre> getGenresByMovieId(int movieId) {
        return movieDao.getGenresByMovieId(movieId);
    }

}
