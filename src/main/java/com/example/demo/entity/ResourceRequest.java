package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resource_requests")
public class ResourceRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String resourceType;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User requestedBy;
    
    @Column(nullable = false)
    private LocalDateTime startTime;
    
    @Column(nullable = false)
    private LocalDateTime endTime;
    
    @Column(nullable = false)
    private String purpose;
    
    @Column(nullable = false)
    private String status = "PENDING";
    
    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL)
    private ResourceAllocation allocation;
    
    public ResourceRequest() {}
    
    public ResourceRequest(String resourceType, User requestedBy, LocalDateTime startTime, 
                          LocalDateTime endTime, String purpose, String status) {
        this.resourceType = resourceType;
        this.requestedBy = requestedBy;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
        this.status = status;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getResourceType() {
        return resourceType;
    }
    
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
    public User getRequestedBy() {
        return requestedBy;
    }
    
    public void setRequestedBy(User requestedBy) {
        this.requestedBy = requestedBy;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public ResourceAllocation getAllocation() {
        return allocation;
    }
    
    public void setAllocation(ResourceAllocation allocation) {
        this.allocation = allocation;
    }
}