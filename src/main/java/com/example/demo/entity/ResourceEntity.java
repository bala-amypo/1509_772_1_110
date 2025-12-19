package com.example.demo.entity;

public class ResourceEntity {
    
}
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String resourceName;

    @Column(nullable = false)
    private String resourceType;

    @Column(nullable = false)
    private Integer capacity;

    private String location;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Constructors
    public Resource() {
    }

    public Resource(String resourceName, String resourceType, Integer capacity, String location, LocalDateTime createdAt) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.capacity = capacity;
        this.location = location;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Validation
    @PrePersist
    public void validate() throws IllegalArgumentException {
        if (resourceName == null || resourceName.isEmpty()) {
            throw new IllegalArgumentException("resourceName cannot be empty");
        }

        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be >= 1");
        }

        if (resourceType == null || resourceType.isEmpty()) {
            throw new IllegalArgumentException("resourceType is required");
        }
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", resourceName='" + resourceName + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", capacity=" + capacity +
                ", location='" + location + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
