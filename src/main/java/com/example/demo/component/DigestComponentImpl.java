package com.example.demo.component;

import com.example.demo.model.UserCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;

@Component
@RequiredArgsConstructor
public class DigestComponentImpl implements DigestComponent {

    private final PasswordEncoder encoder;

    @Override
    public String runCreate(UserCreate userCreate) {
        return encoder.encode(CharBuffer.wrap(userCreate.getPassword()));
    }

    @Override
    public void matches(char[] password, String passwordEncoded) {
        boolean valid = encoder.matches(CharBuffer.wrap(password), passwordEncoded);
        if (!valid) {
            throw new RuntimeException("Senha inválida");
        }
    }
}
