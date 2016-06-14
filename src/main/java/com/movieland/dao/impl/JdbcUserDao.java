package com.movieland.dao.impl;

import com.movieland.dao.UserDao;
import com.movieland.dao.impl.mapper.UserRowMapper;
import com.movieland.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserDao implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getUserByIdSql;

    @Autowired
    private String getUserByNameAndPasswordSql;

    UserRowMapper userRowMapper = new UserRowMapper();

    @Override
    public User getUserById(int userId) {
        User user = jdbcTemplate.queryForObject(getUserByIdSql, new Object[]{userId}, userRowMapper);
        return user;
    }

    @Override
    public User tryGetUserByUsernameAndPassword(String userName, String userPassword) {
        try {
            User user = jdbcTemplate.queryForObject(getUserByNameAndPasswordSql, new Object[]{userName, userPassword}, userRowMapper);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
