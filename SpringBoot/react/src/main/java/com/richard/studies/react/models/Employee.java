package com.richard.studies.react.models;

import javax.validation.constraints.NotBlank;

public class Employee {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String company;
    private Integer salary;

    public Employee() {

    }

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

    public void setId(Long id) {
        if(this.id == null) {
            this.id = id;
        }
    }
}
