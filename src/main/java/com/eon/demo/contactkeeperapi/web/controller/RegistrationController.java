package com.eon.demo.contactkeeperapi.web.controller;

import com.eon.demo.contactkeeperapi.service.UserService;
import com.eon.demo.contactkeeperapi.web.mapper.UserMapper;
import com.eon.demo.contactkeeperapi.web.model.AccessTokenResource;
import com.eon.demo.contactkeeperapi.web.model.RegistrationResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegistrationController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping("/public/register")
    public ResponseEntity<AccessTokenResource> register(@RequestBody RegistrationResource registrationResource) {
        log.info("Attempting to register user...");
        var user = userService.register(userMapper.mapToUser(registrationResource));
        var uri = linkTo(methodOn(UserController.class).getCurrentUser()).toUri();
        var response = AccessTokenResource.builder()
                .token(userService.authenticate(registrationResource.getEmail(), registrationResource.getPassword()))
                .build();
        log.info("Registration {} successful", user);
        return ResponseEntity.created(uri).body(response);
    }

}
