package com.example.demo.service;

import com.example.demo.component.DigestComponent;
import com.example.demo.component.TokenComponent;
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

public interface LoginService {
    UserRepository getUserRepository();
    TokenComponent getTokenComponent();
    DigestComponent getDigestComponent();
    String authenticate(Login login);
    boolean validate(Login login, User user);
}
