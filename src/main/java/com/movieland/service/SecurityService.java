package com.movieland.service;

import com.movieland.entity.User;

public interface SecurityService {
    String getSecurityToken(String userName, String userPassword);
    boolean isSecurityTokenValid(String securityToken);
    User getUserByToken(String securityToken);
}
