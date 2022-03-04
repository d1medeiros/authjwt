package com.example.demo.service;

import com.example.demo.component.DigestComponent;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.component.UserFactory;
import com.example.demo.model.UserCreate;
import com.example.demo.model.UserUpdate;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final DigestComponent digestComponent;

    @Override
    public User create(UserCreate userCreate) {
        String password = digestComponent.runCreate(userCreate);
        User user = userFactory.build(userCreate.getUsername(), password);
        user.setDateCreate(LocalDateTime.now());
        user.setLastUpdate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public List<User> find(JsonNode userFind) {
        return null;
    }

    @Override
    public User update(String id, UserUpdate userUpdate) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setActive(userUpdate.isActive());
        user.setLastUpdate(LocalDateTime.now());
        return userRepository.save(user);
    }
}
