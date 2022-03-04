package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserCreate;
import com.example.demo.model.UserUpdate;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface UserController {
    UserService getUserService();
    ResponseEntity<User> create(UserCreate userCreate);
    ResponseEntity<User> update(UserUpdate userCreate);
    ResponseEntity<User> find(JsonNode userFind);
}
