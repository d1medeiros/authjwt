package com.example.demo.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class TokenComponent {

    private static final String TZ = "America/Sao_Paulo";

    @Value("${auth.jwt.expiration}")
    private Long expirationTime;

    @Value("${auth.jwt.secret}")
    private String secret;

    public String generate(String username) {
        var current = getCurrentDateTimePlus(0L);
        var expiration = getCurrentDateTimePlus(expirationTime);

        return JWT.create()
                .withIssuer("NGAIA")
                .withClaim("username", username)
                .withIssuedAt(current)
                .withExpiresAt(expiration)
                .sign(Algorithm.HMAC256(secret));
    }

    private Date getCurrentDateTimePlus(Long delta) {
        return Date.from(LocalDateTime
                .ofInstant(Instant
                        .ofEpochMilli(System.currentTimeMillis() + delta), ZoneId.of(TZ))
                .atZone(ZoneId.of(TZ)).toInstant());
    }
}
