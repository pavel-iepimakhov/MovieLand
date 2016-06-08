package com.movieland.dao.impl.mapper;

import com.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MovieRowMapper implements RowMapper<Movie> {
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setMovieId(resultSet.getInt("movie_id"));
        movie.setMovieNameEng(resultSet.getString("movie_name_eng"));
        movie.setMovieNameRus(resultSet.getString("movie_name_rus"));
        movie.setMovieYear(resultSet.getInt("movie_year"));
        ArrayList<String> genres = new ArrayList<>();
        for(String genre : resultSet.getString("genres").split(", ")) {
            genres.add(genre);
        }
        movie.setGenres(genres);
        return movie;
    }
}
