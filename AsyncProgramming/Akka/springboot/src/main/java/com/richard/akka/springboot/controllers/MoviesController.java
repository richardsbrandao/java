package com.richard.akka.springboot.controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.pattern.PatternsCS;
import akka.util.Timeout;
import com.richard.akka.springboot.actors.factories.MovieActorFactories;
import com.richard.akka.springboot.configs.SpringExtension;
import com.richard.akka.springboot.controllers.requests.CreateMovieRequest;
import com.richard.akka.springboot.controllers.responses.MovieResponse;
import com.richard.akka.springboot.daos.MovieDao;
import com.richard.akka.springboot.models.Movie;
import com.richard.akka.springboot.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.CompletionStage;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("movies")
public class MoviesController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        Iterable<Movie> movies = movieService.findAll();
        return new ResponseEntity<>(
                StreamSupport.stream(movies.spliterator(), false).map(MovieResponse::new),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<MovieResponse> create(@RequestBody CreateMovieRequest requestBody) {
        Movie movie = movieService.save(requestBody.toModel());
        return new ResponseEntity<>(
                new MovieResponse(movie),
                HttpStatus.CREATED
        );
    }
}
