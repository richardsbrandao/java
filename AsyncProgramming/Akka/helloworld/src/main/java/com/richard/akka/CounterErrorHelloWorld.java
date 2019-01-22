package com.richard.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.PatternsCS;
import com.richard.akka.errors.actors.CounterActorSupervisor;
import com.richard.akka.errors.actors.messages.Add;
import com.richard.akka.errors.actors.messages.GetResult;
import com.richard.akka.errors.actors.messages.Minus;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class CounterErrorHelloWorld {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ActorSystem system = ActorSystem.create();
        ActorRef presenter = system.actorOf(Props.create(CounterActorSupervisor.class), "counter-actor-supervisor");

        presenter.tell(new Add("81"), ActorRef.noSender());
        presenter.tell(new Add("3"), ActorRef.noSender());
//        presenter.tell(new Minus("blah"), ActorRef.noSender());
        presenter.tell(new Minus("90"), ActorRef.noSender());
//        presenter.tell(new Minus("1000"), ActorRef.noSender());
        presenter.tell(new Add("123"), ActorRef.noSender());
        presenter.tell(new Minus("54"), ActorRef.noSender());

        PatternsCS.ask(presenter, new GetResult(), 2000)
                    .toCompletableFuture()
                    .thenAccept(
                            (result) -> System.out.println("Result: " + result)
                    );
    }
}
