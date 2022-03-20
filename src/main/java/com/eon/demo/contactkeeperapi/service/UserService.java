package com.eon.demo.contactkeeperapi.service;

import com.eon.demo.contactkeeperapi.data.model.UserEntity;
import com.eon.demo.contactkeeperapi.data.repository.UserRepository;
import com.eon.demo.contactkeeperapi.exceptionhandling.model.AuthorizationException;
import com.eon.demo.contactkeeperapi.exceptionhandling.model.BadRequestException;
import com.eon.demo.contactkeeperapi.exceptionhandling.model.UnauthorizedException;
import com.nimbusds.jose.KeyLengthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserEntity register(UserEntity user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String authenticate(String email, String password) {
        if (!userRepository.existsByEmail(email)) {
            throw new UnauthorizedException("Email or password incorrect");
        }
        var user = userRepository.getByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("Email or password incorrect");
        }

        try {
            return jwtService.createJwt(user);
        }
        catch (KeyLengthException e) {
            throw new UnauthorizedException("Email or password incorrect");
        }
    }

    public UserEntity getUser(long id) {

        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Could not find user"));
    }

    public UserEntity getUserByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new BadRequestException("User does not exist");
        }
        return userRepository.getByEmail(email);
    }

    public UserEntity updateUser(String firstName, String lastName, String email) {
        var currentUser = getCurrentUser();
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);

        return userRepository.save(currentUser);
    }

    public UserEntity getCurrentUser() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!userRepository.existsByEmail(email)) {
            throw new AuthorizationException("Current user does not exist");
        }

        return userRepository.getByEmail(email);

    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

}
