package com.richard.studies.react.controllers;

import com.richard.studies.react.exceptions.UniqueNameException;
import com.richard.studies.react.models.Employee;
import com.richard.studies.react.services.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.function.Predicate;

@RestController
@RequestMapping(EmployeesController.EMPLOYEES_PATH)
public class EmployeesController {
    public static final String EMPLOYEES_PATH = "/api/employees";
    public static final String EMPLOYEES_BY_ID_PATH = EMPLOYEES_PATH + "/{employeeId}";

    @Autowired
    private EmployeesRepository employeesRepository;

    @GetMapping
    public Flux<Employee> findAll() {
        return employeesRepository.findAll();
    }

    @GetMapping(EmployeesController.EMPLOYEES_BY_ID_PATH)
    public Mono<Employee> findById(@PathVariable("employeeId") Long employeeId) {
        return employeesRepository.findById(employeeId);
    }

    @PostMapping
    public Mono<Employee> save(@Valid @RequestBody Employee employee) {
        return employeesRepository.add(employee)
                .onErrorReturn((throwable) -> throwable instanceof UniqueNameException,
                                employeesRepository.findByName(employee.getName()));
    }
}
