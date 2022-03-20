package com.eon.demo.contactkeeperapi.exceptionhandling;

import com.eon.demo.contactkeeperapi.exceptionhandling.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {


    private static final String INTERNAL_SERVER_ERROR = "GENERAL_SERVER_ERROR";
    private static final String FORBIDDEN = "FORBIDDEN";
    private static final String BAD_REQUEST = "BAD_REQUEST";
    private static final String UNAUTHORIZED = "UNAUTHORIZED";

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException e, HttpServletRequest request) {
        log.error("Internal Server Error", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder().code(INTERNAL_SERVER_ERROR).msg(e.getMessage()).build());
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        log.error("Authorization error", e);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder().code(FORBIDDEN).msg(e.getMessage()).build());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e, HttpServletRequest request) {
        log.error("Authentication error", e);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder().code(UNAUTHORIZED).msg(e.getMessage()).build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
        log.error("Bad request", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder().code(BAD_REQUEST).msg(e.getMessage()).build());
    }



}
