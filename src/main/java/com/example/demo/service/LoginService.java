package com.example.demo.service;

import com.example.demo.component.TokenComponent;
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.repository.LoginRepository;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    LoginRepository getLoginRepository();
    TokenComponent getTokenComponent();
    ResponseEntity authenticate(Login login);
    boolean validate(Login login, User user);
}
