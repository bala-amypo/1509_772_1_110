package com.example.demo.service.impl;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.stereotype.Service;

@Service
public class ResourceRequestServiceImpl extends ResourceRequestService {

    public ResourceRequestServiceImpl(ResourceRequestRepository requestRepository,
                                      UserRepository userRepository) {
        super(requestRepository, userRepository);
    }

    @Override
    public ResourceRequest createRequest(Long userId, ResourceRequest request) {

        // ✅ FIX 1: purpose validation (t10_addRequest)
        if (request.getPurpose() == null || request.getPurpose().trim().isEmpty()) {
            throw new ValidationException("purpose required");
        }

        // fetch user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        request.setRequestedBy(user);

        // ✅ FIX 2: default status (t37_requestStatusDefault)
        request.setStatus("PENDING");

        return requestRepository.save(request);
    }
}
