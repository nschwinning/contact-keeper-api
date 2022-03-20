package com.eon.demo.contactkeeperapi.service;

import com.eon.demo.contactkeeperapi.data.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    public void testCreateJWT() throws Exception {
        var jwt = jwtService.createJwt(UserEntity.builder().email("nils.schwinning@gmx.de").firstName("Nils").lastName("Schwinning").build());

        System.out.println(jwt);
    }

}
