package com.eon.demo.contactkeeperapi.web.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class UserResource {

    private long id;
    private String email;
    private String firstName;
    private String lastName;

}
