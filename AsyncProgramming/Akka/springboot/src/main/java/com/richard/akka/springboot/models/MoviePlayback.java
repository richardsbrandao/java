package com.richard.akka.springboot.models;

import lombok.Value;

@Value
public class MoviePlayback {
    private Long id;
    private User user;
    private Movie movie;
    private MovieState state;
    private Long seem;
}
