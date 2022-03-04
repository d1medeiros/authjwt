package com.example.demo.component;


import com.example.demo.model.UserCreate;

public interface DigestComponent {
    UserCreate run(UserCreate userCreate);
    void match(char[] password, String password1);
}