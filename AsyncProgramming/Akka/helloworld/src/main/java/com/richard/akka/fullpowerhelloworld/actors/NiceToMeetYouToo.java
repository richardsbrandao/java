package com.richard.akka.fullpowerhelloworld.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.richard.akka.fullpowerhelloworld.actors.messages.Greeted;
import com.richard.akka.fullpowerhelloworld.actors.messages.Greeting;

public class NiceToMeetYouToo extends UntypedActor {
    private final ActorRef presenter;

    NiceToMeetYouToo() {
        presenter = getContext().actorOf(Props.create(Presenter.class), "presenter");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof Greeting) {
            String whoGreet = ((Greeting) message).getWhoGreet();
            String greeted = ((Greeting) message).getGreeted();
            System.out.println(String.format("%s: Nice to mee you too %s", greeted, whoGreet));
            presenter.tell(new Greeted(), getSelf());
        } else unhandled(message);
    }
}
