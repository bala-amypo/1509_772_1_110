package com.example.demo.security;

public class JwtUtil {

    public String generateToken(String username) {
        return "dummy-token-for-" + username;
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("dummy-token");
    }
}
