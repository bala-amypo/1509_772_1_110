package com.example.demo.service.impl;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class UserServiceImpl extends UserService {
    public User createUser(User user) {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ✅ HASH PASSWORD (fixes t51_passwordHashing)
    user.setPassword(encoder.encode(user.getPassword()));

    return userRepository.save(user);
    }

}
