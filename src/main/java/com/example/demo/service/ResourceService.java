package com.example.demo.service;

import com.example.demo.entity.ResourceEntity;
import com.example.demo.repository.ResourceRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    private final ResourceRepository repository;

    public ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }

    public ResourceEntity create(ResourceEntity resource) {

        // Rule 1: resourceName unique
        if (repository.existsByResourceName(resource.getResourceName())) {
            throw new RuntimeException("Resource already exists");
        }

        // Rule 2: resourceType required
        if (resource.getResourceType() == null || resource.getResourceType().isBlank()) {
            throw new RuntimeException("resourceType is required");
        }

        // Rule 3: capacity >= 1
        if (resource.getCapacity() == null || resource.getCapacity() < 1) {
            throw new RuntimeException("capacity must be >= 1");
        }

        return repository.save(resource);
    }
}
