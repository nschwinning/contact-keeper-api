package com.eon.demo.contactkeeperapi.web.controller;

import com.eon.demo.contactkeeperapi.service.ContactService;
import com.eon.demo.contactkeeperapi.service.UserService;
import com.eon.demo.contactkeeperapi.web.mapper.UserMapper;
import com.eon.demo.contactkeeperapi.web.model.UserResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/api/auth")
    public ResponseEntity<UserResource> getCurrentUser() {
        return ResponseEntity.ok(userMapper.mapToUserResource(userService.getCurrentUser()));
    }

    @PutMapping("/api/auth")
    public ResponseEntity<UserResource> updateUser(@RequestBody UserResource userResource) {
        return ResponseEntity.ok(userMapper
                .mapToUserResource(userService
                        .updateUser(userResource.getFirstName(), userResource.getLastName(), userResource.getEmail())));
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResource>> getUsers() {
        var users = userService.getAllUsers().stream()
                .map(user -> userMapper.mapToUserResource(user))
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(users);
    }

}
