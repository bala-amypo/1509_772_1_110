package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ResourceControllerImpl {
    @PostMapping
    public String createResource() {
        return "Resource created";
    }
    @GetMapping
    public String getResources() {
        return "List of all resources";
    }
    @GetMapping("/{id}")
    public String getResource(@PathVariable Long id) {
        return "Resource with ID: " + id;
    }
}
