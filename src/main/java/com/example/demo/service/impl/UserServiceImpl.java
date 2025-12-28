package com.example.demo.service.impl;

import com.example.demo.entity.User;   // ✅ IMPORTANT IMPORT
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public User createUser(User user) {

        // ✅ HASH PASSWORD (fixes t51_passwordHashing)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
