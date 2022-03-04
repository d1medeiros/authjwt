package com.example.demo.controller;

import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.service.LoginService;
import com.example.demo.service.LoginServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Getter
@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {
    private final LoginServiceImpl loginService;

    @PostMapping
    public ResponseEntity<User> authenticate(@Valid @RequestBody Login login) {
        User user = loginService.authenticate(login);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
