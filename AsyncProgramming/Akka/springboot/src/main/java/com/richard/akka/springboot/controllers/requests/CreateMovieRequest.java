package com.richard.akka.springboot.controllers.requests;

import com.richard.akka.springboot.models.Movie;
import lombok.Setter;

@Setter
public class CreateMovieRequest {
    private String name;
    private Long duration;

    public Movie toModel() {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDuration(duration);
        return movie;
    }
}
