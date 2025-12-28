package com.example.demo.controller;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/allocations")
public class ResourceAllocationController {

    private final ResourceAllocationService allocationService;

    public ResourceAllocationController(ResourceAllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping("/auto/{requestId}")
    public ResponseEntity<ResourceAllocation> autoAllocate(@PathVariable Long requestId) {
        ResourceAllocation allocation = allocationService.autoAllocate(requestId);
        return ResponseEntity.ok(allocation);
    }

    @GetMapping
    public ResponseEntity<List<ResourceAllocation>> getAllAllocations() {
        List<ResourceAllocation> allocations = allocationService.getAllAllocations();
        return ResponseEntity.ok(allocations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceAllocation> getAllocation(@PathVariable Long id) {
        ResourceAllocation allocation = allocationService.getAllocation(id);
        return ResponseEntity.ok(allocation);
    }
}
