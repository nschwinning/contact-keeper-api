package com.eon.demo.contactkeeperapi.configuration;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWKSet jwkSet() {

        return new JWKSet();

    }

}
