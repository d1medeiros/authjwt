package com.example.demo.service;

import com.example.demo.component.DigestComponent;
import com.example.demo.component.TokenComponent;
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    private String username = "username";
    private char[] loginPassword = null;
    private String userPassword = "userPassword";
    @InjectMocks
    LoginServiceImpl loginService;
    @Mock
    UserRepository userRepository;
    @Mock
    TokenComponent tokenComponent;
    @Mock
    DigestComponent digestComponent;
    @Mock
    private Login login;
    @Mock
    private User user;


    @Test
    @DisplayName("autentica com sucesso")
    void authenticate() {
        when(login.getUsername()).thenReturn(username);
        when(login.getPassword()).thenReturn(loginPassword);
        when(user.getPassword()).thenReturn(userPassword);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        User result = loginService.authenticate(login);
        verify(digestComponent).matches(loginPassword, userPassword);
        assertNotNull(result);
    }

    @Test
    @DisplayName("não autentica se não achar usuário")
    void authenticate2() {
        when(login.getUsername()).thenReturn(username);
        when(login.getPassword()).thenReturn(loginPassword);
        when(user.getPassword()).thenReturn(userPassword);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        assertThrows(RuntimeException.class, () -> loginService.authenticate(login));
        verify(digestComponent, never()).matches(any(), anyString());
    }
}