package com.movieland.controller;

import com.movieland.entity.request.GetSecurityTokenRequest;
import com.movieland.util.JsonConverterService;
import com.movieland.security.SecurityService;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class SecurityController {

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private JsonConverterService jsonConverterService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/v1/auth", consumes = "application/json;charset=UTF-8", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getSecurityToken(@RequestBody String body) {
        GetSecurityTokenRequest request = jsonConverterService.jsonToObject(body, GetSecurityTokenRequest.class);
        String userName = request.getUserName();
        String userPassword = request.getUserPassword();
        LOGGER.info("Method getSecurityToken was invoked for user {}", userName);
        String token = securityService.getSecurityToken(userName, userPassword);
        if(token != null) {
            MDC.put("userName", userName);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
