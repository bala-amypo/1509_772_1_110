package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @PostMapping("/register")
    public String registerUser() {
        return "User registered";
    }
    @GetMapping
    public String getUsers() {
        return "List of users";
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "User with ID: " + id;
    }
}
