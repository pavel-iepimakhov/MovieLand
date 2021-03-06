package com.movieland.controller.exceptionhandler;

import com.movieland.controller.MovieController;
import com.movieland.util.Message;
import com.movieland.util.JsonConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private JsonConverterService jsonConverterService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        LOGGER.info("Exception occured: ", e);
        return new ResponseEntity<>(jsonConverterService.objectToJson(new Message("Exception: " + e.getMessage())), HttpStatus.BAD_REQUEST);
    }
}
