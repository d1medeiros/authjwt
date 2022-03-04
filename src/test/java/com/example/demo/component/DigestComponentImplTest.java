package com.example.demo.component;

import com.example.demo.UnitTest;
import com.example.demo.model.UserCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

class DigestComponentImplTest extends UnitTest {

    @InjectMocks
    DigestComponentImpl digestComponent;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(digestComponent, "encoder", new BCryptPasswordEncoder());
    }

    @Test
    @DisplayName("Testa criptografia")
    void validateCrypto() {
        String password = "123456";
        UserCreate user = UserCreate.builder().username("rodrigo").password(password.toCharArray()).build();
        String hash = digestComponent.runCreate(user);
        Assertions.assertNotNull(hash);
    }

    @Test
    @DisplayName("Verifica se as senhas combinam")
    void validateMatch() {
        String password = "123456";
        UserCreate user = UserCreate.builder().username("rodrigo").password(password.toCharArray()).build();
        String hash = digestComponent.runCreate(user);
        digestComponent.matches(password.toCharArray(), hash);
    }

    @Test
    @DisplayName("A verificação de senha deve falhar quando as senhas forem diferentes")
    void shouldFailOnMatch() {
        String password = "123456";
        UserCreate user = UserCreate.builder().username("rodrigo").password(password.toCharArray()).build();
        String hash = digestComponent.runCreate(user);
        Assertions.assertThrows(RuntimeException.class, () -> digestComponent.matches("1234567".toCharArray(), hash));
    }
}