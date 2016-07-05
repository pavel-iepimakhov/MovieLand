package com.movieland.util.impl;

import com.movieland.util.TokenGeneratorService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {
    @Override
    public String getToken() {
        return UUID.randomUUID().toString();
    }
}
