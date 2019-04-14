package com.richard.studies.react.controllers;

import com.richard.studies.react.models.Employee;
import com.richard.studies.react.services.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
    @Autowired
    private EmployeesRepository employeesRepository;

    @GetMapping
    public Flux<Employee> findAll() {
        return employeesRepository.findAll();
    }

    @GetMapping("/{employeeId}")
    public Mono<Employee> findById(@PathVariable("employeeId") Long employeeId) {
        return employeesRepository.findById(employeeId);
    }
}
