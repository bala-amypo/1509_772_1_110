package com.example.demo.service.impl;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ResourceRequestServiceImpl implements ResourceRequestService {
    
    private final ResourceRequestRepository resourceRequestRepository;
    private final UserRepository userRepository;
    
    public ResourceRequestServiceImpl(ResourceRequestRepository resourceRequestRepository,
                                     UserRepository userRepository) {
        this.resourceRequestRepository = resourceRequestRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional
    public ResourceRequest createRequest(Long userId, ResourceRequest request) {
        // Find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        
        // Validate required fields
        if (request.getPurpose() == null || request.getPurpose().isEmpty()) {
            throw new IllegalArgumentException("Purpose is required");
        }
        
        // Validate time range
        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        
        // Set user and default status
        request.setRequestedBy(user);
        request.setStatus("PENDING");
        
        return resourceRequestRepository.save(request);
    }
    
    @Override
    public List<ResourceRequest> getRequestsByUser(Long userId) {
        // Check if user exists
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        
        return resourceRequestRepository.findByRequestedById(userId);
    }
    
    @Override
    public ResourceRequest getRequest(Long id) {
        return resourceRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource request not found with id: " + id));
    }
    
    @Override
    @Transactional
    public ResourceRequest updateRequestStatus(Long requestId, String status) {
        ResourceRequest request = getRequest(requestId);
        
        // Validate status
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        
        request.setStatus(status);
        return resourceRequestRepository.save(request);
    }
    
    private boolean isValidStatus(String status) {
        return status.equals("PENDING") || 
               status.equals("APPROVED") || 
               status.equals("REJECTED");
    }
}