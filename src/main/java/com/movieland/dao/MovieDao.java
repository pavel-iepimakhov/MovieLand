package com.movieland.dao;

import com.movieland.entity.Genre;
import com.movieland.entity.Movie;
import com.movieland.entity.MovieReview;

import java.util.List;

public interface MovieDao {
    List<Movie> getAllMovies();
    Movie getMovieById(int movieId);
    List<Genre> getGenresByMovieId(int movieId);
}

