package com.movieland.dao;

import com.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getAllMovies();

    Movie getMovieById(int movieId);
}
