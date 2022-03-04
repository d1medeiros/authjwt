package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserCreate;
import com.example.demo.model.UserUpdate;
import com.example.demo.service.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Getter
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userService;

    @Override
    @PostMapping
    @SneakyThrows
    public ResponseEntity<User> create(@Valid @RequestBody UserCreate userCreate) {
        User user = userService.create(userCreate);
        return ResponseEntity.created(new URI("/" + user.getId())).body(user);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @Valid @RequestBody UserUpdate userUpdate) {
        return ResponseEntity.ok(userService.update(id, userUpdate));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<User>> find(JsonNode userFind) {
        return ResponseEntity.ok(userService.find(userFind));
    }
}
