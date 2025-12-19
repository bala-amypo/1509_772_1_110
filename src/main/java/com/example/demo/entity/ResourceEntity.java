package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.example.demo.entity.ResourceEntity;

@Entity
@Table(name = "resources")
public class ResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String resourceName;

    @Column(nullable = false)
    private String resourceType;

    @Column(nullable = false)
    private Integer capacity;

    private String location;

    private LocalDateTime createdAt;

    public ResourceEntity() {}

    @PrePersist
    public void prePersist() {
        if (capacity == null || capacity < 1) {
            throw new IllegalArgumentException("Capacity must be >= 1");
        }
        this.createdAt = LocalDateTime.now();
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
