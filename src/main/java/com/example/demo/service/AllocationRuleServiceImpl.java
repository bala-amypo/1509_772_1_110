package com.example.demo.service.impl;

import com.example.demo.entity.AllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {
    
    private final AllocationRuleRepository allocationRuleRepository;
    
    public AllocationRuleServiceImpl(AllocationRuleRepository allocationRuleRepository) {
        this.allocationRuleRepository = allocationRuleRepository;
    }
    
    @Override
    @Transactional
    public AllocationRule createRule(AllocationRule rule) {
        // Check for duplicate rule name
        if (allocationRuleRepository.existsByRuleName(rule.getRuleName())) {
            throw new IllegalArgumentException("Rule with name " + rule.getRuleName() + " already exists");
        }
        
        // Validate priority weight
        if (rule.getPriorityWeight() == null || rule.getPriorityWeight() < 0) {
            throw new IllegalArgumentException("Priority weight must be at least 0");
        }
        
        // Validate rule type
        if (!isValidRuleType(rule.getRuleType())) {
            throw new IllegalArgumentException("Invalid rule type: " + rule.getRuleType());
        }
        
        return allocationRuleRepository.save(rule);
    }
    
    @Override
    public AllocationRule getRule(Long id) {
        return allocationRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Allocation rule not found with id: " + id));
    }
    
    @Override
    public List<AllocationRule> getAllRules() {
        return allocationRuleRepository.findAll();
    }
    
    private boolean isValidRuleType(String ruleType) {
        return ruleType.equals("FIRSTAVAILABLE") ||
               ruleType.equals("PRIORITYBASED") ||
               ruleType.equals("ROUNDROBIN");
    }
}