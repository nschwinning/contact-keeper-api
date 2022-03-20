package com.eon.demo.contactkeeperapi.exceptionhandling.model;

public class BadRequestException extends ApplicationException{

    public BadRequestException(String message) {
        super(message);
    }
}
