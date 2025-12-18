package com.example.demo.controller;

import com.example.demo.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public com.example.demo.entity.Resource createResource(com.example.demo.entity.Resource resource) {
        if (resourceRepository.existsByResourceName(resource.getResourceName())) {
            throw new RuntimeException("Resource already exists");
        }
        return resourceRepository.save(resource);
    }

    public List<com.example.demo.entity.Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public com.example.demo.entity.Resource getResourceById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
    }
}
