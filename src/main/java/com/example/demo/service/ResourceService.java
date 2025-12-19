import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public Resource createResource(String resourceName, String resourceType, Integer capacity, String location) {
        
        if (resourceRepository.existsByResourceName(resourceName)) {
            throw new IllegalArgumentException("Resource with name '" + resourceName + "' already exists");
        }

        Resource resource = new Resource(resourceName, resourceType, capacity, location, LocalDateTime.now());
        return resourceRepository.save(resource);
    }
}
