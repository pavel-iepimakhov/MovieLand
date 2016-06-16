package com.movieland.dao.impl.mapper;

import com.movieland.entity.MovieReview;
import com.movieland.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: 16.06.2016 unit test
public class MovieReviewRowMapper implements RowMapper<MovieReview> {
    public MovieReview mapRow(ResultSet resultSet, int i) throws SQLException {
        MovieReview movieReview = new MovieReview();
        movieReview.setMovieId(resultSet.getInt("movie_id"));
        movieReview.setMovieId(resultSet.getInt("review_id"));
        movieReview.setReviewText(resultSet.getString("review_text"));
        movieReview.setUser(new User(resultSet.getInt("user_id"), resultSet.getString("user_name")));
        return movieReview;
    }
}
