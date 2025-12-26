package com.example.demo.service;

import com.example.demo.entity.AllocationRule;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AllocationRuleRepository;

import java.util.List;

public class AllocationRuleService {

    private final AllocationRuleRepository ruleRepository;

    public AllocationRuleService(AllocationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public AllocationRule createRule(AllocationRule rule) {

        if (ruleRepository.existsByRuleName(rule.getRuleName())) {
            throw new ValidationException("rule exists");
        }

        if (rule.getPriorityWeight() != null && rule.getPriorityWeight() < 0) {
            throw new ValidationException("priority must be >= 0");
        }

        return ruleRepository.save(rule);
    }

    public AllocationRule getRule(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("rule not found"));
    }

    public List<AllocationRule> getAllRules() {
        return ruleRepository.findAll();
    }
}
