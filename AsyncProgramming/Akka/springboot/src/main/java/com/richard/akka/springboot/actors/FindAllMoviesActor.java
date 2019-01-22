package com.richard.akka.springboot.actors;

import akka.actor.UntypedActor;
import com.richard.akka.springboot.actors.messages.CreateMovieMessage;
import com.richard.akka.springboot.daos.MovieDao;
import com.richard.akka.springboot.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FindAllMoviesActor extends UntypedActor {
    public static final String NAME = "findAllMoviesActor";

    @Autowired
    private MovieDao movieDao;

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof CreateMovieMessage) {
            Movie movie = ((CreateMovieMessage) message).getMovie();
            getSender().tell(movieDao.save(movie), getSelf());
        }
    }
}
