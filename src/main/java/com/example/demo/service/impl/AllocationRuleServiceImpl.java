package com.example.demo.service.impl;

import com.example.demo.repository.AllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;

@Service
public class AllocationRuleServiceImpl extends AllocationRuleService {

    public AllocationRuleServiceImpl(AllocationRuleRepository ruleRepository) {
        super(ruleRepository);
    }
}
