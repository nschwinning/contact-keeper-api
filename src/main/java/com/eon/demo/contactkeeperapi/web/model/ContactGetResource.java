package com.eon.demo.contactkeeperapi.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ContactGetResource {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String type;

}
