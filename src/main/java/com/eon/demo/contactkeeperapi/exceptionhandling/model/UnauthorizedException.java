package com.eon.demo.contactkeeperapi.exceptionhandling.model;

public class UnauthorizedException extends ApplicationException  {

    public UnauthorizedException(String message) {
        super(message);
    }
}
