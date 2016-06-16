package com.movieland.dao;

import com.movieland.entity.User;

public interface UserDao {
    User getUserById(int userId);

    User tryGetUserByUsernameAndPassword(String userName, String userPassword);
}
