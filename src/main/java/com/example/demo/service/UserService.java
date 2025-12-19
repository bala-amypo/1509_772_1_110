package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity create(UserEntity user) {

        // Rule 1: email unique
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with email already exists");
        }

        // Rule 2: default role
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }

        return repository.save(user);
    }
}
