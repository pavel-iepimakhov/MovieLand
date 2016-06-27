package com.movieland.service;

public interface SecurityService {
    String getSecurityToken(String username, String password);
    boolean isSecurityTokenValid(String token);
}
