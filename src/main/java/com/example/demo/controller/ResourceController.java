package com.example.demo.controller;

import com.example.demo.entity.Resource;
import com.example.demo.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    
    private final ResourceService resourceService;
    
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
    
    @PostMapping
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource createdResource = resourceService.createResource(resource);
        return new ResponseEntity<>(createdResource, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResource(@PathVariable Long id) {
        Resource resource = resourceService.getResource(id);
        return ResponseEntity.ok(resource);
    }
    
    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources() {
        List<Resource> resources = resourceService.getAllResources();
        return ResponseEntity.ok(resources);
    }
}