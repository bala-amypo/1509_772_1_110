package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resource_allocations")
public class ResourceAllocation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;
    
    @OneToOne
    @JoinColumn(name = "request_id", nullable = false, unique = true)
    private ResourceRequest request;
    
    @Column(nullable = false)
    private LocalDateTime allocatedAt;
    
    private Boolean conflictFlag = false;
    
    private String notes;
    
    @PrePersist
    protected void onCreate() {
        allocatedAt = LocalDateTime.now();
    }
    
    public ResourceAllocation() {}
    
    public ResourceAllocation(Resource resource, ResourceRequest request, Boolean conflictFlag, String notes) {
        this.resource = resource;
        this.request = request;
        this.conflictFlag = conflictFlag;
        this.notes = notes;
        this.allocatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Resource getResource() {
        return resource;
    }
    
    public void setResource(Resource resource) {
        this.resource = resource;
    }
    
    public ResourceRequest getRequest() {
        return request;
    }
    
    public void setRequest(ResourceRequest request) {
        this.request = request;
    }
    
    public LocalDateTime getAllocatedAt() {
        return allocatedAt;
    }
    
    public void setAllocatedAt(LocalDateTime allocatedAt) {
        this.allocatedAt = allocatedAt;
    }
    
    public Boolean getConflictFlag() {
        return conflictFlag;
    }
    
    public void setConflictFlag(Boolean conflictFlag) {
        this.conflictFlag = conflictFlag;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
}