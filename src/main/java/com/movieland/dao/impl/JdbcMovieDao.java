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
    private String getGenresByMovieIdSql;

    @Autowired
    private String updateAverageMovieRatingSql;

    private MovieRowMapper movieRowMapper = new MovieRowMapper();
    private MoviesRowMapper moviesRowMapper = new MoviesRowMapper();
    private GenreRowMapper genreRowMapper = new GenreRowMapper();


    @Override
    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(getAllMoviesSql, moviesRowMapper);
    }

    @Override
    public Movie getMovieById(int movieId) {
        return jdbcTemplate.queryForObject(getMovieByIdSql, new Object[]{movieId}, movieRowMapper);
    }

    @Override
    public List<Genre> getGenresByMovieId(int movieId) {
        return jdbcTemplate.query(getGenresByMovieIdSql, new Object[]{movieId}, genreRowMapper);
    }

    @Override
    public void updateAverageMovieRating(int movieId) {
        jdbcTemplate.update(updateAverageMovieRatingSql, movieId);
    }

}
