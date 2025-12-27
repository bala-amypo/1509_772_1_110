package com.example.demo.service.impl;

import com.example.demo.repository.ResourceRepository;
import com.example.demo.service.ResourceService;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends ResourceService {

    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        super(resourceRepository);
    }
}
