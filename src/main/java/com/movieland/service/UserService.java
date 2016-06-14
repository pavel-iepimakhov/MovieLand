package com.movieland.service;

import com.movieland.entity.User;

public interface UserService {
    User getUserById(int userId);
    User tryGetUserByUsernameAndPassword(String userName, String userPassword);
}
