package com.richard.akka;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.richard.akka.simplehelloworld.actors.HelloWorld;

public class SimpleHelloWorld {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();

        system.actorOf(Props.create(HelloWorld.class));

        system.terminate();
    }
}
