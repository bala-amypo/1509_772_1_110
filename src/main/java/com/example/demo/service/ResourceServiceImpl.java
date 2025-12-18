package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ResourceRepository;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository repo;
    public ResourceServiceImpl(ResourceRepository repo) {
        this.repo = repo;
    }
    public com.example.demo.entity.Resource createResource(com.example.demo.entity.Resource resource) {
        if (repo.existsByResourceName(resource.getResourceName())) {
            throw new RuntimeException("exists");
            
        }
        return repo.save(resource);
    }
    public com.example.demo.entity.Resource getResource(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }
    public List<com.example.demo.entity.Resource> getAllResources() {
        return repo.findAll();
    }
    
}
