package com.richard.akka.springboot.services;

import akka.actor.ActorRef;
import akka.pattern.PatternsCS;
import akka.util.Timeout;
import com.richard.akka.springboot.actors.factories.MovieActorFactories;
import com.richard.akka.springboot.actors.messages.CreateMovieMessage;
import com.richard.akka.springboot.actors.messages.FindAllMoviesMessage;
import com.richard.akka.springboot.daos.MovieDao;
import com.richard.akka.springboot.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.concurrent.duration.Duration;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@Service
public class MovieService {
    @Autowired
    private MovieActorFactories movieActorFactories;

    @Autowired
    private MovieDao movieDao;

    public Iterable<Movie> findAll() {
        final ActorRef movieActor = movieActorFactories.create();

        Timeout timeout = new Timeout(Duration.create(1, TimeUnit.SECONDS));
        CompletionStage<Object> moviesFuture = PatternsCS.ask(movieActor, new FindAllMoviesMessage(), timeout);

        return (Iterable<Movie>) moviesFuture.toCompletableFuture().join();

    }

    public Movie save(Movie movie) {
        final ActorRef movieActor = movieActorFactories.create();
        Timeout timeout = new Timeout(Duration.create(1, TimeUnit.SECONDS));
        CompletionStage<Object> savedMovieFuture = PatternsCS.ask(movieActor, new CreateMovieMessage(movie), timeout);
        return (Movie) savedMovieFuture.toCompletableFuture().join();
    }
}
