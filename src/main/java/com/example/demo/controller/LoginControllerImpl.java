package com.example.demo.controller;

import com.example.demo.dto.LoginResponseDTO;
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
    public ResponseEntity<LoginResponseDTO> authenticate(@Valid @RequestBody Login login) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(LoginResponseDTO
                        .builder()
                        .sessionToken(loginService.authenticate(login))
                        .build());
    }
}
