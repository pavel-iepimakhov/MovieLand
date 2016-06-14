package com.movieland.service;

public interface SecurityService {
    String getSecurityToken(String userName, String userPassword);
    boolean isSecurityTokenValid(String securityToken);
}
