package com.example.demo.service;

import com.example.demo.model.Resource;
import com.example.demo.model.ResourceAllocation;
import com.example.demo.model.ResourceRequest;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceAllocationService {

    private final ResourceAllocationRepository allocationRepo;
    private final ResourceRepository resourceRepo;
    private final ResourceRequestRepository requestRepo;

    public ResourceAllocationService(ResourceAllocationRepository allocationRepo,
                                     ResourceRepository resourceRepo,
                                     ResourceRequestRepository requestRepo) {
        this.allocationRepo = allocationRepo;
        this.resourceRepo = resourceRepo;
        this.requestRepo = requestRepo;
    }

    public ResourceAllocation autoAllocate(Long requestId) {

        if (allocationRepo.existsByRequestId(requestId)) {
            throw new RuntimeException("Allocation already exists");
        }

        ResourceRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        Resource resource = resourceRepo.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No resource available"));

        ResourceAllocation allocation = new ResourceAllocation();
        allocation.setRequest(request);
        allocation.setResource(resource);
        allocation.setNotes("Auto allocated");

        return allocationRepo.save(allocation);
    }

    public ResourceAllocation getById(Long id) {
        return allocationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));
    }
}
