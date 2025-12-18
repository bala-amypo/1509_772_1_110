package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/requests")
public class ResourceRequest {
    @PostMapping("/{userID}")
    public String createRequest(@PathVariable Long userID) {
        return "Request created for User: " + userID;
    }
    @GetMapping("/user/{userID}")
    public String getRequest(@PathVariable Long userID) {
        return "Request for User: " + userID;
    }
    @GetMapping("/{id}")
    public String getRequestById(@PathVariable Long id) {
        return "Request with ID: " + id;
    }
    @PutMapping("/status/{requestID}")
    public String updateRequestStatus(@PathVariable Long requestID) {
        return "Request status updated for Request ID: " + requestID;
    }
}
