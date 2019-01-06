package com.richard.akka.fullpowerhelloworld.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.richard.akka.fullpowerhelloworld.actors.messages.Greeting;

public class NiceToMeetYou extends UntypedActor {
    private final ActorRef niceTooMeetYouToo;

    NiceToMeetYou() {
        niceTooMeetYouToo = getContext().actorOf(Props.create(NiceToMeetYouToo.class), "niceToMeetYouToo");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof Greeting) {
            String whoGreet = ((Greeting) message).getWhoGreet();
            String greeted = ((Greeting) message).getGreeted();
            System.out.println(String.format("%s: Nice to mee you %s", whoGreet, greeted));
            niceTooMeetYouToo.tell(message, getSelf());
        } else unhandled(message);
    }
}
