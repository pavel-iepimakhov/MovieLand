package com.movieland.controller;

import com.movieland.entity.User;
import com.movieland.entity.response.AddReportRequestResponse;
import com.movieland.report.ReportFormatEnum;
import com.movieland.report.ReportService;
import com.movieland.report.ReportTypeEnum;
import com.movieland.security.SecurityService;
import com.movieland.service.MovieService;
import com.movieland.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class ReportController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonConverterService jsonConverterService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/v1/report", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> addReportRequest(@RequestHeader(value = "Security-Token", required = false) String securityToken,
                                                   @RequestParam("type") ReportTypeEnum reportType,
                                                   @RequestParam(value = "date", required = false) LocalDate reportDate,
                                                   @RequestParam("format") ReportFormatEnum reportFormat) {
        ResponseEntity<String> unauthorizedResponse = new ResponseEntity<>(jsonConverterService.objectToJson(new ErrorMessage("Not authorized")), HttpStatus.UNAUTHORIZED);
        if (securityToken != null) {
            User user = securityService.getUserByToken(securityToken);
            if (user != null && user.isAdmin()) {
                AddReportRequestResponse response = reportService.addReportRequest(reportType, reportDate, reportFormat, user);
                return new ResponseEntity<>(jsonConverterService.objectToJson(response), HttpStatus.OK);
            } else return unauthorizedResponse;
        } else return unauthorizedResponse;
    }

    @RequestMapping(value = "/v1/report", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> checkReportRequestStatus(@RequestHeader(value = "Security-Token", required = false) String securityToken) {
        return null;
    }

    @RequestMapping(value = "/v1/report", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> getReportLink(@RequestHeader(value = "Security-Token", required = false) String securityToken) {
        return null;
    }

    @RequestMapping(value = "/v1/report", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> removeReportRequest(@RequestHeader(value = "Security-Token", required = false) String securityToken) {
        return null;
    }
}
