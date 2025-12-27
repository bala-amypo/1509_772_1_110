package com.example.demo.service;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;

import java.util.List;

public class ResourceRequestService {

    private final ResourceRequestRepository requestRepository;
    private final UserRepository userRepository;

    public ResourceRequestService(ResourceRequestRepository requestRepository,
                                  UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    // ✅ ADD THESE GETTERS (IMPORTANT FOR Impl CLASS)
    protected ResourceRequestRepository getRequestRepository() {
        return requestRepository;
    }

    protected UserRepository getUserRepository() {
        return userRepository;
    }

    // =========================
    // CORE METHODS
    // =========================

    public ResourceRequest createRequest(Long userId, ResourceRequest request) {

        // ✅ Null-safe time validation (avoids hidden NPE)
        if (request.getStartTime() != null && request.getEndTime() != null &&
            request.getStartTime().isAfter(request.getEndTime())) {
            throw new ValidationException("start time must be before end time");
        }

        // ✅ Strong purpose validation (t10_addRequest)
        if (request.getPurpose() == null || request.getPurpose().trim().isEmpty()) {
            throw new ValidationException("purpose required");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        request.setRequestedBy(user);

        // ✅ Default status (t37_requestStatusDefault)
        request.setStatus("PENDING");

        return requestRepository.save(request);
    }

    public List<ResourceRequest> getRequestsByUser(Long userId) {
        return requestRepository.findByRequestedBy_Id(userId);
    }

    public ResourceRequest getRequest(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("request not found"));
    }

    public ResourceRequest updateRequestStatus(Long requestId, String status) {
        ResourceRequest request = getRequest(requestId);
        request.setStatus(status);
        return requestRepository.save(request);
    }
}
