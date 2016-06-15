package com.movieland.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.movieland.entity.User;
import com.movieland.service.SecurityService;
import com.movieland.service.TokenGeneratorService;
import com.movieland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenGeneratorService tokenGeneratorService;

    private Cache<String, User> tokenCache = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.HOURS)
            .build();

    @Override
    public String getSecurityToken(String userName, String userPassword) {
        User user = userService.tryGetUserByUsernameAndPassword(userName, userPassword);
        String token = null;
        if(user != null) {
            token = tokenGeneratorService.getToken();
            tokenCache.put(token, user);
        }
        return token;
    }

    @Override
    public boolean isSecurityTokenValid(String securityToken) {
        User user = tokenCache.getIfPresent(securityToken);
        return user != null;
    }

    @Override
    public User getUserByToken(String securityToken) {
        return tokenCache.getIfPresent(securityToken);
    }

}
