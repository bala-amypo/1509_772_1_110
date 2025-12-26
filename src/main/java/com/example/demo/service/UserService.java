package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ValidationException;
import java.util.List;

public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User registerUser(User user) {
        if (repo.existsByEmail(user.getEmail())) {
            throw new ValidationException("email exists");
        }
        return repo.save(user);
    }

    public User getUser(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
