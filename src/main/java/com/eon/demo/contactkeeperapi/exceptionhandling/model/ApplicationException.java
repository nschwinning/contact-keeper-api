package com.eon.demo.contactkeeperapi.exceptionhandling.model;

public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

}
