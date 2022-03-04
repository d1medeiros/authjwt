package com.example.demo.component;

import com.example.demo.model.UserCreate;

public interface DigestComponent {
    String runCreate(UserCreate userCreate);

    void matches(char[] password, String passwordEncoded);
}