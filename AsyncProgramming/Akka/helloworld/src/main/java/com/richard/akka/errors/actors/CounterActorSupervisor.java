package com.richard.akka.errors.actors;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class CounterActorSupervisor extends UntypedActor {
    private final ActorRef calculator;
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private OneForOneStrategy strategy = new OneForOneStrategy(10, Duration.create(10, TimeUnit.MINUTES),
            t -> {
                    if (t instanceof NumberFormatException) {
                        log.info("Invalid number, ignoring!");
                        return OneForOneStrategy.resume();
                    } else if (t instanceof RuntimeException) {
                        log.error("Invalid operation, restarting!");
                        return OneForOneStrategy.restart();
                    }
                log.error("Invalid message, Shutting down!");
                return OneForOneStrategy.stop();
            });

    public CounterActorSupervisor() {
        this.calculator = getContext().actorOf(Props.create(CalculatorActor.class));
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        calculator.tell(message, getSender());
    }
}
