package com.example.demo.controller;

import com.example.demo.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    // POST /api/resources  → create resource
    @PostMapping
    public com.example.demo.entity.Resource createResource(@RequestBody com.example.demo.entity.Resource resource) {
        return resourceService.createResource(resource);
    }

    // GET /api/resources → list all resources
    @GetMapping
    public List<com.example.demo.entity.Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    // GET /api/resources/{id} → get resource by id
    @GetMapping("/{id}")
    public com.example.demo.entity.Resource getResourceById(@PathVariable Long id) {
        return resourceService.getResourceById(id);
    }
}
