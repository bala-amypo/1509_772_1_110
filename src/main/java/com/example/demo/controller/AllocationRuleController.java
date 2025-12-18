package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {


@PostMapping
public String createRule() {
return "Allocation rule created";
}


@GetMapping
public String getAllRules() {
return "List of allocation rules";
}


@GetMapping("/{id}")
public String getRule(@PathVariable Long id) {
return "Allocation rule with id: " + id;
}
}