package com.movieland.service;

import com.movieland.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(int movieId);
}
