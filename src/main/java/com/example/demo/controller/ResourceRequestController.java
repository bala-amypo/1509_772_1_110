package com.example.demo.controller;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.service.ResourceRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ResourceRequestController {
    
    private final ResourceRequestService resourceRequestService;
    
    public ResourceRequestController(ResourceRequestService resourceRequestService) {
        this.resourceRequestService = resourceRequestService;
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<ResourceRequest> createRequest(
            @PathVariable Long userId,
            @RequestBody ResourceRequest request) {
        ResourceRequest createdRequest = resourceRequestService.createRequest(userId, request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResourceRequest>> getRequestsByUser(@PathVariable Long userId) {
        List<ResourceRequest> requests = resourceRequestService.getRequestsByUser(userId);
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResourceRequest> getRequest(@PathVariable Long id) {
        ResourceRequest request = resourceRequestService.getRequest(id);
        return ResponseEntity.ok(request);
    }
    
    @PutMapping("/status/{requestId}")
    public ResponseEntity<ResourceRequest> updateRequestStatus(
            @PathVariable Long requestId,
            @RequestParam String status) {
        ResourceRequest updatedRequest = resourceRequestService.updateRequestStatus(requestId, status);
        return ResponseEntity.ok(updatedRequest);
    }
}