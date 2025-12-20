package com.example.demo.controller;

import com.example.demo.entity.ResourceEntity;
import com.example.demo.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResourceEntity> create(@RequestBody ResourceEntity resource) {
        return new ResponseEntity<>(service.create(resource), HttpStatus.CREATED);
    }
}
