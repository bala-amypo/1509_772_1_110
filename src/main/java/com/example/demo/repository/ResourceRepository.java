package com.example.demo.repository;

import com.example.demo.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
    boolean existsByResourceName(String resourceName);
}
