package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Resource;
import com.example.demo.service.ResourceService;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final ResourceService service;
    public ResourceController(ResourceService service) {
        this.service = service;
    }
    @PostMapping
    public Resource create(@RequestBody Resource resource) {
        return service.createResource(resource);
    }
    @GetMapping
    public List<Resource> getAll() {
        return service.getAllResources();
    }
    @GetMapping("/{id}")
    public Resource get(@PathVariable Long id) {
        return service.getResource(id);
    }
    
}
