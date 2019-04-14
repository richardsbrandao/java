package com.richard.studies.react.models;

public class Employee {
    private Long id;
    private String name;
    private String company;
    private Integer salary;

    public Employee(Long id, String name, String company, Integer salary) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public Integer getSalary() {
        return salary;
    }
}
