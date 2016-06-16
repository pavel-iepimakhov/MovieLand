package com.movieland.controller;

import com.movieland.util.JsonConverterService;
import com.movieland.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class SecurityController {

    @Autowired
    private JsonConverterService jsonConverterService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/v1/auth", consumes = "application/json;charset=UTF-8", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getSecurityToken(@RequestBody String body) {
        Map<String,String> userPassMap = jsonConverterService.getStringMapFromJson(body);
        String token = securityService.getSecurityToken(userPassMap.get("username"), userPassMap.get("password"));
        if(token != null) {
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
