package com.poc.akka.actor;

import akka.actor.UntypedActor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author by Pritom Gogoi
 */
@Component("ErrorHandler")
@Scope("prototype")
public class ErrorHandler extends UntypedActor {


    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Map) {
            logError((Map)message);
        } else {
            unhandled(message);
        }
    }

    private void logError(Map<String, String> message) {
        System.out.println("Error in processing : "+message);
        context().stop(self());
    }
}