package com.movieland.dao.impl;


import com.movieland.dao.MovieDao;
import com.movieland.dao.impl.mapper.GenreRowMapper;
import com.movieland.dao.impl.mapper.MovieReviewRowMapper;
import com.movieland.dao.impl.mapper.MovieRowMapper;
import com.movieland.dao.impl.mapper.MoviesRowMapper;
import com.movieland.entity.Genre;
import com.movieland.entity.Movie;
import com.movieland.entity.MovieReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Repository
public class JdbcMovieDao implements MovieDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getAllMoviesSql;

    @Autowired
    private String getMovieByIdSql;

    @Autowired
    private String getReviewsByMovieIdSql;

    @Autowired
    private String getGenresByMovieIdSql;

    private MovieRowMapper movieRowMapper = new MovieRowMapper();
    private MoviesRowMapper moviesRowMapper = new MoviesRowMapper();
    private MovieReviewRowMapper movieReviewRowMapper = new MovieReviewRowMapper();
    private GenreRowMapper genreRowMapper = new GenreRowMapper();


    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(getAllMoviesSql, moviesRowMapper);
    }

    public Movie getMovieById(int movieId) {
        Movie movie = jdbcTemplate.queryForObject(getMovieByIdSql, new Object[]{movieId}, movieRowMapper);
        return movie;
    }

    public List<MovieReview> getReviewsByMovieId(int movieId) {
        List<MovieReview> movieReviews = jdbcTemplate.query(getReviewsByMovieIdSql, new Object[]{movieId}, movieReviewRowMapper);
        return movieReviews;
    }

    public List<Genre> getGenresByMovieId(int movieId) {
        List<Genre> movieGenres = jdbcTemplate.query(getGenresByMovieIdSql, new Object[]{movieId}, genreRowMapper);
        return movieGenres;
    }

}
