package com.example.demo.controller;

import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.model.Login;
import com.example.demo.service.LoginService;
import org.springframework.http.ResponseEntity;

public interface LoginController {
    LoginService getLoginService();
    ResponseEntity<LoginResponseDTO>  authenticate(Login login);
}
