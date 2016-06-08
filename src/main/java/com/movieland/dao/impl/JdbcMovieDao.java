package com.movieland.dao.impl;

import com.movieland.dao.MovieDao;
import com.movieland.dao.impl.mapper.GetAllMoviesRowMapper;
import com.movieland.entity.Movie;
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

    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(getAllMoviesSql, new GetAllMoviesRowMapper());
    }
}
