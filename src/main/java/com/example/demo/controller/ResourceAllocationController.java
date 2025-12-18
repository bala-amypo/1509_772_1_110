package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/allocations")
public class ResourceAllocationController {


@PostMapping("/auto/{requestId}")
public String autoAllocate(@PathVariable Long requestId) {
return "Resource auto-allocated for request: " + requestId;
}


@GetMapping("/{id}")
public String getAllocation(@PathVariable Long id) {
return "Allocation with id: " + id;
}
}