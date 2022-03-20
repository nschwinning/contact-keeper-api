package com.eon.demo.contactkeeperapi.configuration;

import com.eon.demo.contactkeeperapi.properties.SecretProperties;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@EnableConfigurationProperties(SecretProperties.class)
@RequiredArgsConstructor
@Configuration
public class OAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SecretProperties secretProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .antMatcher("/api/**").authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .oauth2ResourceServer().jwt();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withSecretKey(new SecretKeySpec(secretProperties.getKey().getBytes(StandardCharsets.UTF_8), "HS512"))
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }


}
