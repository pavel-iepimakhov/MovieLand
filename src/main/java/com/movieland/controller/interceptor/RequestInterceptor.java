package com.movieland.controller.interceptor;

import com.movieland.entity.User;
import com.movieland.security.SecurityService;
import com.movieland.util.TokenGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestInterceptor extends HandlerInterceptorAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TokenGeneratorService tokenGeneratorService;

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        String requestId = tokenGeneratorService.getToken();
        LOGGER.info("Request URI: {}",request.getRequestURI());
        MDC.put("requestId", requestId);
        String securityToken = request.getHeader("Security-Token");
        if(securityToken != null) {
            User user = securityService.getUserByToken(securityToken);
            MDC.put("userName", user.getUserName());
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
