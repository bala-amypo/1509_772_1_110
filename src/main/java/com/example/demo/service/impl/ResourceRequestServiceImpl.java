package com.example.demo.service.impl;

import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.stereotype.Service;

@Service
public class ResourceRequestServiceImpl extends ResourceRequestService {

    public ResourceRequestServiceImpl(ResourceRequestRepository requestRepository,
                                      UserRepository userRepository) {
        super(requestRepository, userRepository);
    }
}
