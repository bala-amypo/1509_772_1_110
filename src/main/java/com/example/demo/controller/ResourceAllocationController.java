package com.example.demo.controller;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/allocations")
public class ResourceAllocationController {
    
    private final ResourceAllocationService resourceAllocationService;
    
    public ResourceAllocationController(ResourceAllocationService resourceAllocationService) {
        this.resourceAllocationService = resourceAllocationService;
    }
    
    @PostMapping("/auto/{requestId}")
    public ResponseEntity<ResourceAllocation> autoAllocate(@PathVariable Long requestId) {
        ResourceAllocation allocation = resourceAllocationService.autoAllocate(requestId);
        return new ResponseEntity<>(allocation, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResourceAllocation> getAllocation(@PathVariable Long id) {
        ResourceAllocation allocation = resourceAllocationService.getAllocation(id);
        return ResponseEntity.ok(allocation);
    }
    
    @GetMapping
    public ResponseEntity<List<ResourceAllocation>> getAllAllocations() {
        List<ResourceAllocation> allocations = resourceAllocationService.getAllAllocations();
        return ResponseEntity.ok(allocations);
    }
}