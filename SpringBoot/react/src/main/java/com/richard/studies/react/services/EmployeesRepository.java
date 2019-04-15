package com.richard.studies.react.services;

import com.richard.studies.react.exceptions.UniqueNameException;
import com.richard.studies.react.models.Employee;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeesRepository {
    private Map<Long, Employee> employees;
    private Long sequence;

    public EmployeesRepository() {
        this.employees = new HashMap<Long, Employee>() {{
            put(1L, new Employee(1L, "Joao", "SA1", 5000));
            put(2L, new Employee(2L, "Maria", "SA2", 3000));
            put(3L, new Employee(2L, "Leila", "SA3", 1100));
            put(4L, new Employee(2L, "Erick", "SA1", 7000));
            put(5L, new Employee(2L, "Gustavo", "SA2", 1500));
            put(6L, new Employee(2L, "Vitor", "SA1", 3500));
            put(7L, new Employee(2L, "Luiza", "SA2", 4000));
            put(8L, new Employee(2L, "Rafaela", "SA1", 7500));
        }};
        this.sequence = 9L;
    }

    public Flux<Employee> findAll() {
        return Flux.fromIterable(this.employees.values());
    }

    public Mono<Employee> findById(Long id) {
        return Mono.just(this.employees.get(id));
    }

    public Mono<Employee> add(Employee employeeToAdd) {
        return Mono.fromCallable(() -> {
            boolean found = this.employees.values().stream()
                    .anyMatch(employee -> employee.getName().equals(employeeToAdd.getName()));
            if(found) {
                throw new UniqueNameException();
            }
            employeeToAdd.setId(++this.sequence);
            this.add(employeeToAdd);
            return employeeToAdd;
        });
    }

    public Employee findByName(String name) {
        return this.employees.values().stream().filter(employee -> employee.getName().equals(name))
                    .findFirst().get();
    }
}
