package com.example.demo.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.example.demo.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TokenComponentTest extends UnitTest {

    private static final String USERNAME = "test";
    private static final String SECRET = "secret";
    private static final String TZ = "America/Sao_Paulo";
    private static final Long EXPIRATION_TIME = 21600000L;

    @InjectMocks
    private TokenComponent tokenComponent;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(tokenComponent, "secret", SECRET);
        ReflectionTestUtils.setField(tokenComponent, "expirationTime", EXPIRATION_TIME);
    }

    @Test
    @DisplayName("valida geracao do token")
    public void validateTokenGeneration() {
        var token = tokenComponent.generate(USERNAME);
        assertNotNull(token);
    }

    @Test
    @DisplayName("valida assinatura do token")
    public void validateTokenSignature() {
        var token = tokenComponent.generate(USERNAME);
        // Throws an exception if is not valid
        Algorithm
                .HMAC256(SECRET)
                .verify(JWT.decode(token));
    }

    @Test
    @DisplayName("deve lançar exceção SignatureVerificationException ao validar a assinatura do token")
    public void shouldThrowSignatureVerificationException() {
        var token = tokenComponent.generate(USERNAME);
        assertThrows(SignatureVerificationException.class, () -> Algorithm
                .HMAC256("wrong-secret")
                .verify(JWT.decode(token)));
    }

    @Test
    @DisplayName("valida se o tempo de expiração do token está correto")
    public void validateExpirationTime() {
        var token = tokenComponent.generate(USERNAME);
        var decoded = JWT.decode(token);
        var iat = decoded.getClaim("iat").asLong();
        var exp = decoded.getClaim("exp").asLong();
        var ldtIat = LocalDateTime.ofInstant(Instant.ofEpochSecond(iat), ZoneId.of(TZ));
        var ldtExp = LocalDateTime.ofInstant(Instant.ofEpochSecond(exp), ZoneId.of(TZ));

        // 6 hours == 21600000 millisecond (EXPIRATION_TIME constant)
        assertEquals(ldtExp, ldtIat.plusHours(6));
    }

    @Test
    @DisplayName("valida se o token foi gerado com a claim de username correta")
    public void validateUsernameClaim() {
        var token = tokenComponent.generate(USERNAME);
        var decoded = JWT.decode(token);
        assertEquals(USERNAME, decoded.getClaim("username").asString());
    }
}
