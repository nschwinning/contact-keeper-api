package com.eon.demo.contactkeeperapi.service;

import com.eon.demo.contactkeeperapi.data.model.UserEntity;
import com.eon.demo.contactkeeperapi.properties.SecretProperties;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

@RequiredArgsConstructor
@Service
public class JwtService {

    private final String audience = "kasjdhajsdnkausdhamnsdn";
    private final String issuer = "https://contactkeeperapi.eon.com";
    private final SecretProperties secretProperties;

    public String createJwt(UserEntity user) throws KeyLengthException {
        // Create MAC-signer with the private key
        JWSSigner signer = new MACSigner(secretProperties.getKey());

        // Prepare JWS object with simple string as payload
        JWSObject jwsObject = new JWSObject(new JWSHeader.Builder(JWSAlgorithm.HS256).build(), getPayload(user));

        // Compute the RSA signature
        try {
            jwsObject.sign(signer);

            // To serialize to compact form, produces something like
            // eyJhbGciOiJSUzI1NiJ9.SW4gUlNBIHdlIHRydXN0IQ.IRMQENi4nJyp4er2L
            // mZq3ivwoAjqa1uUkSBKFIX7ATndFF5ivnt-m8uApHO4kfIFOrW7w2Ezmlg3Qd
            // maXlS9DhN0nUk_hGI3amEjkKd0BWYCB8vfUbUv0XGjQip78AI4z1PrFRNidm7
            // -jPDm5Iq0SZnjKjCNS5Q15fokXZc8u0A
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Could not create JWT.", e);
        }
    }

    private Payload getPayload(UserEntity user) {
        ZonedDateTime iat = ZonedDateTime.now(ZoneId.of("Europe/Berlin"));
        ZonedDateTime exp = iat.plusMinutes(60).minusSeconds(5);
        ZonedDateTime nbf = iat.minusSeconds(5);

        JWTClaimsSet claims = new JWTClaimsSet.Builder().audience(audience)
                .subject(user.getEmail())
                .issuer(issuer)
                .claim("first_name", user.getFirstName())
                .claim("last_name", user.getLastName())
                .issueTime(GregorianCalendar.from(iat).getTime())
                .expirationTime(GregorianCalendar.from(exp).getTime())
                .notBeforeTime(GregorianCalendar.from(nbf).getTime())
                .build();

        return new Payload(claims.toJSONObject());
    }

}
