package com.eon.demo.contactkeeperapi.web.mapper;

import com.eon.demo.contactkeeperapi.data.model.UserEntity;
import com.eon.demo.contactkeeperapi.web.model.RegistrationResource;
import com.eon.demo.contactkeeperapi.web.model.UserResource;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResource mapToUserResource(UserEntity user) {
        return UserResource.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public UserEntity mapToUser(RegistrationResource registrationResource){
        return UserEntity.builder()
                .email(registrationResource.getEmail())
                .firstName(registrationResource.getFirstName())
                .lastName(registrationResource.getLastName())
                .password(registrationResource.getPassword())
                .build();
    }

}
