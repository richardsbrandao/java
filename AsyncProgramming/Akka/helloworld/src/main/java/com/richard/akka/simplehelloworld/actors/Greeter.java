package com.richard.akka.simplehelloworld.actors;

import akka.actor.UntypedActor;
import com.richard.akka.simplehelloworld.actors.types.GreeterMessageTypes;

public class Greeter extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        if(message == GreeterMessageTypes.GREET) {
            System.out.println("Hello World");
            getSender().tell(GreeterMessageTypes.DONE, getSelf());
        } else unhandled(message);
    }
}
