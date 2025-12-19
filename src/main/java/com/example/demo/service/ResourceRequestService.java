package com.example.demo.service;
import com.example.demo.model.User;

import com.example.demo.model.ResourceRequest;
import com.example.demo.model.User;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceRequestService {

    private final ResourceRequestRepository requestRepository;
    private final UserRepository userRepository;

    public ResourceRequestService(ResourceRequestRepository requestRepository,
                                  UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public ResourceRequest createRequest(Long userId, ResourceRequest request) {

        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new RuntimeException("startTime must be before endTime");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        request.setRequestedBy(user);

        return requestRepository.save(request);
    }

    public List<ResourceRequest> getRequestsByUser(Long userId) {
        return requestRepository.findByRequestedById(userId);
    }

    public ResourceRequest getRequestById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    public ResourceRequest updateStatus(Long requestId, String status) {
        ResourceRequest request = getRequestById(requestId);
        request.setStatus(status);
        return requestRepository.save(request);
    }
}
