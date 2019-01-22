package com.richard.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.richard.akka.errors.actors.CounterActorSupervisor;
import com.richard.akka.errors.actors.messages.Add;
import com.richard.akka.errors.actors.messages.Minus;

public class CounterErrorHelloWorld {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();
        ActorRef presenter = system.actorOf(Props.create(CounterActorSupervisor.class), "counter-actor-supervisor");

        presenter.tell(new Add("81"), ActorRef.noSender());
        presenter.tell(new Add("3"), ActorRef.noSender());
        presenter.tell(new Minus("blah"), ActorRef.noSender());
        presenter.tell(new Minus("90"), ActorRef.noSender());
        presenter.tell(new Minus("1000"), ActorRef.noSender());
        presenter.tell(new Add("123"), ActorRef.noSender());
        presenter.tell(new Minus("52"), ActorRef.noSender());
    }
}
