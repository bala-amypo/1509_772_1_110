package com.example.demo.service;

import com.example.demo.entity.ResourceEntity;
import com.example.demo.repository.ResourceRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public ResourceEntity createResource(
            String resourceName,
            String resourceType,
            Integer capacity,
            String location
    ) {
        if (resourceRepository.existsByResourceName(resourceName)) {
            throw new IllegalArgumentException(
                "Resource with name '" + resourceName + "' already exists"
            );
        }

        ResourceEntity resource = new ResourceEntity();
        resource.setResourceName(resourceName);
        resource.setResourceType(resourceType);
        resource.setCapacity(capacity);
        resource.setLocation(location);

        return resourceRepository.save(resource);
    }
}
