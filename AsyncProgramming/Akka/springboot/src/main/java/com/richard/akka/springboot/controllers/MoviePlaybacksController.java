package com.richard.akka.springboot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie-playbacks/{moviePlaybackId}")
public class MoviePlaybacksController {
    @PostMapping
    public ResponseEntity<String> createOrUpdate() {
        return null;
    }

    @PostMapping(value = "/ping")
    public ResponseEntity<String> ping() {
        return null;
    }
}
