package com.eon.demo.contactkeeperapi.exceptionhandling.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {

    private String code;
    private String msg;

}
