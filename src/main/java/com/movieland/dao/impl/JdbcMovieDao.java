package com.movieland.dao.impl;

import com.movieland.dao.MovieDao;
import com.movieland.dao.impl.mapper.MoviesRowMapper;
import com.movieland.entity.Movie;
import com.movieland.entity.MovieReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcMovieDao implements MovieDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getAllMoviesSql;

    @Autowired
    private String getMovieByIdSql;

    private MoviesRowMapper moviesRowMapper = new MoviesRowMapper();

    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(getAllMoviesSql, moviesRowMapper);
    }

    @Override
    public Movie getMovieById(int movieId) {
        Movie movie = jdbcTemplate.queryForObject(getMovieByIdSql, new Object[]{movieId}, moviesRowMapper);
        return movie;
    }

    public List<MovieReview> getNReviewsByMovieId(){
        return null;
    }
}
