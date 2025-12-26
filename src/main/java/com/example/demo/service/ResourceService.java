package com.example.demo.service;

import com.example.demo.entity.Resource;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service 
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Resource createResource(Resource resource) {

        if (resourceRepository.existsByResourceName(resource.getResourceName())) {
            throw new ValidationException("resource exists");
        }

        if (resource.getCapacity() == null || resource.getCapacity() < 1) {
            throw new ValidationException("capacity must be >= 1");
        }

        if (resource.getResourceType() == null) {
            throw new ValidationException("resourceType required");
        }

        return resourceRepository.save(resource);
    }

    public Resource getResource(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("resource not found"));
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }
}
