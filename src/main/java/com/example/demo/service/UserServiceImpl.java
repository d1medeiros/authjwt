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
        return userRepository.save(user);
    }

    @Override
    public List<User> find(JsonNode userFind) {
        return null;
    }

    @Override
    public User update(Long id, UserUpdate userUpdate) {
        User user = userRepository.getById(id);
        user.setActive(userUpdate.isActive());
        return userRepository.save(user);
    }
}
