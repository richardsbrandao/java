package com.richard.akka.errors.actors;

import akka.actor.UntypedActor;
import com.richard.akka.errors.actors.messages.Add;
import com.richard.akka.errors.actors.messages.GetResult;
import com.richard.akka.errors.actors.messages.Minus;

public class CalculatorActor extends UntypedActor {
    private Integer total;

    public CalculatorActor() {
        this.total = 0;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Add) {
            Add add = (Add) message;
            sum(add);
            System.out.println("Plus: " + add.getValue());
        } else if (message instanceof Minus) {
            Minus minus = (Minus) message;
            subtract(minus);
            System.out.println("Minus: " + minus.getValue());
        } else if (message instanceof GetResult) {
            if(this.total % 2 != 0) {
                throw new ArithmeticException("Odds numbers are terrible");
            }
            getSender().tell(total, getSelf());
        } else unhandled(message);
    }

    private void subtract(Minus message) {
        Integer value = Integer.valueOf(message.getValue());
        if(value == 1000) {
            throw new RuntimeException("Prohibited number :D");
        }
        this.total -= Integer.valueOf(message.getValue());
    }

    private void sum(Add message) {
        Integer value = Integer.valueOf(message.getValue());
        if(value == 1000) {
            throw new ArithmeticException("Prohibited number :D");
        }
        this.total += value;
    }
}
