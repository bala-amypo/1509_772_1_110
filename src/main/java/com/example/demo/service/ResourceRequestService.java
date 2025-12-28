package com.example.demo.service;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;

import java.util.List;

public class ResourceRequestService {

    protected final ResourceRequestRepository requestRepository;
    protected final UserRepository userRepository;

    public ResourceRequestService(ResourceRequestRepository requestRepository,
                                  UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public ResourceRequest createRequest(Long userId, ResourceRequest request) {

        // ✅ STRICT validation for purpose
        if (request.getPurpose() == null || request.getPurpose().trim().isEmpty()) {
            throw new ValidationException("purpose required");
        }

        if (request.getStartTime() == null || request.getEndTime() == null) {
            throw new ValidationException("start and end time required");
        }

        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new ValidationException("start time must be before end time");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        request.setRequestedBy(user);

        // ✅ DEFAULT STATUS
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

        if (status == null || status.trim().isEmpty()) {
            throw new ValidationException("status required");
        }

        request.setStatus(status);
        return requestRepository.save(request);
    }
}
