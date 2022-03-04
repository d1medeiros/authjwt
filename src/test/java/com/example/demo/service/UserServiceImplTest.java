package com.example.demo.service;

import com.example.demo.component.DigestComponent;
import com.example.demo.component.UserFactory;
import com.example.demo.model.User;
import com.example.demo.model.UserCreate;
import com.example.demo.model.UserUpdate;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private String id = "1";
    private String password = "password";

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Mock
    UserFactory userFactory;
    @Mock
    DigestComponent digestComponent;
    @Mock
    User user;
    @Mock
    User savedUser;
    @Mock
    UserCreate userCreate;
    @Mock
    UserUpdate userUpdate;

    @Test
    @DisplayName("Criação de Usuário")
    void create() {
        when(digestComponent.runCreate(userCreate)).thenReturn(password);
        when(userFactory.build(userCreate.getUsername(), password)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        User result = userService.create(userCreate);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Atualização de Usuário")
    void update() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userUpdate.isActive()).thenReturn(false);
        when(userRepository.save(user)).thenReturn(savedUser);
        User result = userService.update(id, userUpdate);
        assertNotNull(result);
    }
}
