package com.richard.akka.simplehelloworld.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.richard.akka.simplehelloworld.actors.types.GreeterMessageTypes;

public class HelloWorld extends UntypedActor {

    @Override
    public void preStart() throws Exception {
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        greeter.tell(GreeterMessageTypes.GREET, getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == GreeterMessageTypes.DONE) {
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }
}
