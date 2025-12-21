package com.example.demo.controller;

import com.example.demo.entity.AllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {
    
    private final AllocationRuleService allocationRuleService;
    
    public AllocationRuleController(AllocationRuleService allocationRuleService) {
        this.allocationRuleService = allocationRuleService;
    }
    
    @PostMapping
    public ResponseEntity<AllocationRule> createRule(@RequestBody AllocationRule rule) {
        AllocationRule createdRule = allocationRuleService.createRule(rule);
        return new ResponseEntity<>(createdRule, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AllocationRule> getRule(@PathVariable Long id) {
        AllocationRule rule = allocationRuleService.getRule(id);
        return ResponseEntity.ok(rule);
    }
    
    @GetMapping
    public ResponseEntity<List<AllocationRule>> getAllRules() {
        List<AllocationRule> rules = allocationRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
}