package com.example.demo.security;

import java.util.Map;

public class JwtUtil {
    private String secretKey;
    private long validityInMs;
    
    public JwtUtil(String secretKey, long validityInMs) {
        this.secretKey = secretKey;
        this.validityInMs = validityInMs;
    }
    
    public String generateToken(Long userId, String email, String role) {
        return "test-token-" + userId + "-" + email;
    }
    
    public Map<String, Object> parseClaims(String token) {
        return Map.of("userId", 1L, "email", "test@example.com", "role", "USER");
    }
}