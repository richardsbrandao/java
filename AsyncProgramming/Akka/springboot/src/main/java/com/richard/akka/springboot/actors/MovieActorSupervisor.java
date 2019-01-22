package com.richard.akka.springboot.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.richard.akka.springboot.actors.factories.MovieActorFactories;
import com.richard.akka.springboot.actors.messages.CreateMovieMessage;
import com.richard.akka.springboot.actors.messages.FindAllMoviesMessage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MovieActorSupervisor extends UntypedActor {
    public static final String NAME = "movieActorSupervisor";

    private final ActorRef findAllMoviesActor;
    private final ActorRef createMovieActor;

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public MovieActorSupervisor(MovieActorFactories movieActorFactories) {
        this.findAllMoviesActor = movieActorFactories.createFindAllMoviesActor();
        this.createMovieActor = movieActorFactories.createCreateMovieActor();
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof CreateMovieMessage) {
            createMovieActor.tell(message, getSender());
        } else if (message instanceof FindAllMoviesMessage) {
            findAllMoviesActor.tell(message, getSender());
        } else unhandled(message);
    }
}
