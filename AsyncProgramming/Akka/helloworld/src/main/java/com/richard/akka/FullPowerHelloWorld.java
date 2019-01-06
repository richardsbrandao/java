package com.richard.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.richard.akka.fullpowerhelloworld.actors.Presenter;
import com.richard.akka.fullpowerhelloworld.actors.messages.Meeting;

public class FullPowerHelloWorld {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();

        ActorRef presenter = system.actorOf(Props.create(Presenter.class), "presenter");
        presenter.tell(new Meeting("Richard", "Ketherin"), null);

        system.terminate();
    }
}
