package com.example.demo.service;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import org.springframework.stereotype.Service;
import java.util.List;

public class ResourceAllocationService {

    private final ResourceRequestRepository requestRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceAllocationRepository allocationRepository;

    public ResourceAllocationService(ResourceRequestRepository requestRepository,
                                     ResourceRepository resourceRepository,
                                     ResourceAllocationRepository allocationRepository) {
        this.requestRepository = requestRepository;
        this.resourceRepository = resourceRepository;
        this.allocationRepository = allocationRepository;
    }

    public ResourceAllocation autoAllocate(Long requestId) {

        ResourceRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("request not found"));

        List<Resource> resources =
                resourceRepository.findByResourceType(request.getResourceType());

        if (resources.isEmpty()) {
            throw new RuntimeException("no resource available");
        }

        ResourceAllocation allocation = new ResourceAllocation();
        allocation.setRequest(request);
        allocation.setResource(resources.get(0));
        allocation.setConflictFlag(false);
        allocation.setNotes("Auto allocated");

        request.setStatus("APPROVED");

        return allocationRepository.save(allocation);
    }

    public ResourceAllocation getAllocation(Long id) {
        return allocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("allocation not found"));
    }

    public List<ResourceAllocation> getAllAllocations() {
        return allocationRepository.findAll();
    }
}
