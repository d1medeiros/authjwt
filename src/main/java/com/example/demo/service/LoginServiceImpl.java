package com.example.demo.service;

import com.example.demo.component.DigestComponent;
import com.example.demo.component.TokenComponent;
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final TokenComponent tokenComponent;
    private final DigestComponent digestComponent;

    @Override
    public String authenticate(Login login) {
        User user = userRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new RuntimeException("não encontrado"));
        digestComponent.matches(login.getPassword(), user.getPassword());
        return tokenComponent.generate(user.getUsername());
    }

    @Override
    public boolean validate(Login login, User user) {
        return false;
    }
}
