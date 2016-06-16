package com.movieland.security;

import com.movieland.entity.User;

public interface SecurityService {
    String getSecurityToken(String userName, String userPassword);
    User getUserByToken(String securityToken);
}
