package com.richard.akka.springboot.actors.factories;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.richard.akka.springboot.actors.MovieActor;
import com.richard.akka.springboot.configs.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieActorFactories {
    @Autowired
    private ActorSystem actorSystem;

    public ActorRef create() {
        return actorSystem.actorOf(
                SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props(MovieActor.NAME)
        );
    }
}
