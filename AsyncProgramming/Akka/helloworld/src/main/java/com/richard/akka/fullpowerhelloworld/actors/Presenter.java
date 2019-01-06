package com.richard.akka.fullpowerhelloworld.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.richard.akka.fullpowerhelloworld.actors.messages.Greeted;
import com.richard.akka.fullpowerhelloworld.actors.messages.Greeting;
import com.richard.akka.fullpowerhelloworld.actors.messages.Meeting;

public class Presenter extends UntypedActor {

    private final ActorRef niceToMeetYou;

    Presenter() {
        niceToMeetYou = getContext().actorOf(Props.create(NiceToMeetYou.class), "niceToMeetYou");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Meeting) {
            Greeting greeting = new Greeting(((Meeting) message).getPersonOne(), ((Meeting) message).getPersonTwo());
            niceToMeetYou.tell(greeting, getSelf());
        } else if (message instanceof Greeted) {
            System.out.println("Presented!");
            getContext().stop(getSelf());
        }
    }
}
