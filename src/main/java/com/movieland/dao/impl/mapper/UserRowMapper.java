package com.movieland.dao.impl.mapper;

import com.movieland.entity.Genre;
import com.movieland.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User(resultSet.getInt("user_id"),
                             resultSet.getString("user_email"),
                             resultSet.getString("user_name"),
                             resultSet.getString("user_password"),
                             resultSet.getString("user_role")
        );
        return user;
    }
}
