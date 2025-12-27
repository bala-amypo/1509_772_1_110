package com.example.demo.service.impl;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends UserService {

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }
}
