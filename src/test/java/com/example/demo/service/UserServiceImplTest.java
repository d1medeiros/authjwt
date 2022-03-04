package com.example.demo.service;

import com.example.demo.component.DigestComponent;
import com.example.demo.model.User;
import com.example.demo.model.UserCreate;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private Long id = 1L;
    private String password = "password";

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Mock
    DigestComponent digestComponent;
    @Mock
    User user;
    @Mock
    User savedUser;
    @Mock
    UserCreate userCreate;

    @Test
    @DisplayName("Criação de Usuário")
    void create() {
        when(digestComponent.runCreate(userCreate)).thenReturn(password);
        when(userRepository.save((user))).thenReturn(user);
        User user = userService.create(userCreate);
        assertNotNull(user);
    }

    @Test
    @DisplayName("Atualização de Usuário")
    void update() {
        when(userRepository.getById(id)).thenReturn(user);
        when(userRepository.save((user))).thenReturn(savedUser);
        User result = userService.create(userCreate);
        assertNotNull(result);
    }
}
