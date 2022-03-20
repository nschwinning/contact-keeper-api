package com.eon.demo.contactkeeperapi.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@Validated
@Data
public class RegistrationResource {

    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

}
