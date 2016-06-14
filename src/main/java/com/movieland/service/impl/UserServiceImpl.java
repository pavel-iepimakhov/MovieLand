package com.movieland.service.impl;

import com.movieland.dao.UserDao;
import com.movieland.entity.User;
import com.movieland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User tryGetUserByUsernameAndPassword(String userName, String userPassword) {
        return userDao.tryGetUserByUsernameAndPassword(userName, userPassword);
    }
}
