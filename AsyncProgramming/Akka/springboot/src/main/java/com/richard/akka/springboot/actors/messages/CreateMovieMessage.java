package com.richard.akka.springboot.actors.messages;

import com.richard.akka.springboot.models.Movie;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMovieMessage {
    private final Movie movie;
}
