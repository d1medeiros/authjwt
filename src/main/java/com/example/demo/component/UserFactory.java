package com.example.demo.component;

import com.example.demo.model.User;
import com.example.demo.model.UserCreate;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserFactory {
    public User build(String username, String password) {
        return User.builder()
                .username(username)
                .password(password)
                .active(true)
                .build();
    }
    public Function<User, User> update(boolean isActive) {
        return user -> {
            user.setActive(isActive);
            return user;
        };
    }
}
