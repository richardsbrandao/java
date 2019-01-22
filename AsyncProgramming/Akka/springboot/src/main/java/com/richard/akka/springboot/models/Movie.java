package com.richard.akka.springboot.models;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
public class Movie {
    @Id
    @Column(columnDefinition="serial")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Long duration;
}
