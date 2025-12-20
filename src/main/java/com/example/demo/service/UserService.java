package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User registerUser(User user) {

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return repository.save(user);
    }
}
