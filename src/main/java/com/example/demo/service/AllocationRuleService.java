package com.example.demo.service;

import com.example.demo.entity.AllocationRule;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AllocationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ✅ ADD THIS
public class AllocationRuleService {

    private final AllocationRuleRepository ruleRepository;

    public AllocationRuleService(AllocationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public AllocationRule createRule(AllocationRule rule) {
        if (ruleRepository.existsByRuleName(rule.getRuleName())) {
            throw new ValidationException("rule exists");
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
