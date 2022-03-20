package com.eon.demo.contactkeeperapi.web.controller;

import com.eon.demo.contactkeeperapi.service.UserService;
import com.eon.demo.contactkeeperapi.web.model.AccessTokenResource;
import com.eon.demo.contactkeeperapi.web.model.AuthenticationResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final UserService userService;

    @PostMapping("/public/authenticate")
    public ResponseEntity<AccessTokenResource> authenticate(@RequestBody AuthenticationResource authenticationResource) {

        return ResponseEntity.ok(AccessTokenResource.builder()
                .token(userService.authenticate(authenticationResource.getEmail(), authenticationResource.getPassword()))
                .build());
    }

}
