import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String resourceName;

    private String resourceType;

    private Integer capacity;

    private String location;

    private LocalDateTime createdAt;

    public Resource() {}

    public Resource(String resourceName, String resourceType, Integer capacity, String location, LocalDateTime createdAt) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.capacity = capacity;
        this.location = location;
        this.createdAt = createdAt;
    }

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

    @PrePersist
    @PreUpdate
    public void validate() throws IllegalArgumentException {
        if (this.resourceName == null || this.resourceName.trim().isEmpty()) {
            throw new IllegalArgumentException("Resource name is required");
        }

        if (this.resourceType == null || this.resourceType.trim().isEmpty()) {
            throw new IllegalArgumentException("Resource type is required");
        }

        if (this.capacity == null || this.capacity < 1) {
            throw new IllegalArgumentException("Capacity must be greater than or equal to 1");
        }
    }
}
