package com.richard.akka.springboot.actors.factories;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.richard.akka.springboot.actors.CreateMovieActor;
import com.richard.akka.springboot.actors.FindAllMoviesActor;
import com.richard.akka.springboot.actors.MovieActorSupervisor;
import com.richard.akka.springboot.configs.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieActorFactories {
    @Autowired
    private ActorSystem actorSystem;

    public ActorRef createMovieActorSupervisor() {
        return actorSystem.actorOf(
                SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props(MovieActorSupervisor.NAME)
        );
    }

    public ActorRef createFindAllMoviesActor() {
        return actorSystem.actorOf(
                SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props(FindAllMoviesActor.NAME)
        );
    }

    public ActorRef createCreateMovieActor() {
        return actorSystem.actorOf(
                SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props(CreateMovieActor.NAME)
        );
    }
}
