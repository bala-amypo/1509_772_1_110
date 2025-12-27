package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 Main field used by tests
    private String name;

    private String email;
    private String password;
    private String role;

    // ✅ No-arg constructor
    public User() {
    }

    // ✅ Constructor required by tests
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // =========================
    // ID
    // =========================
    public Long getId() {
        return id;
    }

    // ✅ Required by tests
    public void setId(Long id) {
        this.id = id;
    }

    // =========================
    // NAME (for tests)
    // =========================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // =========================
    // FULL NAME (for controller)
    // =========================
    public String getFullName() {
        return name;
    }

    public void setFullName(String fullName) {
        this.name = fullName;
    }

    // =========================
    // EMAIL
    // =========================
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // =========================
    // PASSWORD (tests require getter)
    // =========================
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // =========================
    // ROLE
    // =========================
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
