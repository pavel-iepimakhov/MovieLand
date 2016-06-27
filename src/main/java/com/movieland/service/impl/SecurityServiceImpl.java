package com.movieland.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.movieland.entity.Genre;
import com.movieland.entity.User;
import com.movieland.service.SecurityService;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SecurityServiceImpl implements SecurityService {

    private LoadingCache<User, String> movieGenresCache =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(4, TimeUnit.HOURS)
                    .build(new CacheLoader<User, String>() {
                        @Override
                        public String load(User user) throws Exception {
                            return null;
                        }
                    });

    @Override
    public String getSecurityToken(String username, String password) {
        return null;
    }

    @Override
    public boolean isSecurityTokenValid(String token) {
        return false;
    }
}
