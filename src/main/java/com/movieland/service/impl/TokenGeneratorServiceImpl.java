package com.movieland.service.impl;

import com.movieland.entity.User;
import com.movieland.service.TokenGeneratorService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {
    @Override
    public String getToken() {
        return UUID.randomUUID().toString();
    }
}
