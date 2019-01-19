package com.richard.akka.springboot.controllers.responses;

import com.richard.akka.springboot.models.Movie;
import lombok.Getter;

@Getter
public class MovieResponse {
    private Long id;
    private String name;
    private Long duration;

    public MovieResponse(Movie movie) {
       this.id = movie.getId();
       this.name = movie.getName();
       this.duration = movie.getDuration();
    }
}
