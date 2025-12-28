package com.example.demo.controller;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.service.ResourceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ResourceRequestController {

    private final ResourceRequestService requestService;

    public ResourceRequestController(ResourceRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ResourceRequest> createRequest(@PathVariable Long userId, @RequestBody ResourceRequest request) {
        ResourceRequest created = requestService.createRequest(userId, request);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResourceRequest>> getRequestsByUser(@PathVariable Long userId) {
        List<ResourceRequest> requests = requestService.getRequestsByUser(userId);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceRequest> getRequest(@PathVariable Long id) {
        ResourceRequest request = requestService.getRequest(id);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/status/{requestId}")
    public ResponseEntity<ResourceRequest> updateRequestStatus(@PathVariable Long requestId, @RequestParam String status) {
        ResourceRequest updated = requestService.updateRequestStatus(requestId, status);
        return ResponseEntity.ok(updated);
    }
}
