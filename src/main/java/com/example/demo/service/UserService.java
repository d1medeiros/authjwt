package com.example.demo.service;

import com.example.demo.component.DigestComponent;
import com.example.demo.model.User;
import com.example.demo.model.UserCreate;
import com.example.demo.model.UserUpdate;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface UserService {
    UserRepository getUserRepository();
    DigestComponent getDigestComponent();
    User create(UserCreate userCreate);
    List<User> find(JsonNode userFind);
    User update(UserUpdate userCreate);
}


