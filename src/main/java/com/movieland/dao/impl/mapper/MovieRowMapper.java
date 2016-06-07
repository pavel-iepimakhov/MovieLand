package com.movieland.dao.impl.mapper;

import com.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MovieRowMapper implements RowMapper<Movie> {
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie m = new Movie();
        m.setMovieId(resultSet.getInt("movie_id"));
        m.setMovieNameEng(resultSet.getString("movie_name_eng"));
        m.setMovieNameRus(resultSet.getString("movie_name_rus"));
        m.setMovieYear(resultSet.getInt("movie_year"));
        m.setGenres(resultSet.getString("genres").split(", "));
        return m;
    }
}
