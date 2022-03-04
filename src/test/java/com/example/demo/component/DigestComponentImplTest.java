package com.example.demo.component;

import com.example.demo.model.UserCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DigestComponentImplTest {

    @Autowired
    DigestComponentImpl digestComponent;

    @Test
    @DisplayName("Testa criptografia")
    void runCreate() {
        String password = "123456";
        UserCreate user = UserCreate.builder().username("rodrigo").password(password.toCharArray()).build();
        String hash = digestComponent.runCreate(user);
        Assertions.assertNotNull(hash);
    }

    @Test
    @DisplayName("Verifica se as senhas combinam")
    void matches() {
        String password = "123456";
        UserCreate user = UserCreate.builder().username("rodrigo").password(password.toCharArray()).build();
        String hash = digestComponent.runCreate(user);
        digestComponent.matches(password.toCharArray(), hash);
    }

    @Test
    @DisplayName("Verifica se as senhas não combinam")
    void matches2() {
        String password = "123456";
        String password2 = "1234567";
        UserCreate user = UserCreate.builder().username("rodrigo").password(password.toCharArray()).build();
        String hash = digestComponent.runCreate(user);
        digestComponent.matches(password2.toCharArray(), hash);
    }
}