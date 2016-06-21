package com.movieland.controller;

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
        LOGGER.info("Method getSecurityToken was invoked");
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Request body is {}", body);
        }
        Map<String,String> userPassMap = jsonConverterService.getStringMapFromJson(body);
        String userName = userPassMap.get("username");
        String token = securityService.getSecurityToken(userName, userPassMap.get("password"));
        if(token != null) {
            MDC.put("userName", userName);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
