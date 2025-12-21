package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {
    
    private final ResourceRequestRepository resourceRequestRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceAllocationRepository resourceAllocationRepository;
    
    public ResourceAllocationServiceImpl(ResourceRequestRepository resourceRequestRepository,
                                        ResourceRepository resourceRepository,
                                        ResourceAllocationRepository resourceAllocationRepository) {
        this.resourceRequestRepository = resourceRequestRepository;
        this.resourceRepository = resourceRepository;
        this.resourceAllocationRepository = resourceAllocationRepository;
    }
    
    @Override
    @Transactional
    public ResourceAllocation autoAllocate(Long requestId) {
        // Find the request
        ResourceRequest request = resourceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource request not found with id: " + requestId));
        
        // Check if request is approved
        if (!"APPROVED".equals(request.getStatus())) {
            throw new IllegalArgumentException("Cannot allocate resource for request with status: " + request.getStatus());
        }
        
        // Find available resources of the requested type
        List<Resource> availableResources = resourceRepository.findByResourceType(request.getResourceType());
        
        if (availableResources.isEmpty()) {
            throw new ResourceNotFoundException("No available resources of type: " + request.getResourceType());
        }
        
        // Simple allocation strategy: use the first available resource
        Resource selectedResource = availableResources.get(0);
        
        // Check for conflicts (simplified)
        boolean conflictFlag = checkForConflicts(request, selectedResource);
        
        // Create allocation
        ResourceAllocation allocation = new ResourceAllocation();
        allocation.setResource(selectedResource);
        allocation.setRequest(request);
        allocation.setConflictFlag(conflictFlag);
        allocation.setNotes("Automatically allocated");
        
        return resourceAllocationRepository.save(allocation);
    }
    
    @Override
    public ResourceAllocation getAllocation(Long id) {
        return resourceAllocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource allocation not found with id: " + id));
    }
    
    @Override
    public List<ResourceAllocation> getAllAllocations() {
        return resourceAllocationRepository.findAll();
    }
    
    private boolean checkForConflicts(ResourceRequest request, Resource resource) {
        // Simplified conflict check
        // In real implementation, check for overlapping allocations
        return false;
    }
}