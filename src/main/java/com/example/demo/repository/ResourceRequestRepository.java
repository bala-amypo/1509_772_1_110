package com.example.demo.repository;

import com.example.demo.model.ResourceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRequestRepository extends JpaRepository<ResourceRequest, Long> {
    List<ResourceRequest> findByRequestedById(Long userId);
}
