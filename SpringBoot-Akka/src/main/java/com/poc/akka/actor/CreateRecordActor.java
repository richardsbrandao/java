package com.poc.akka.actor;

import akka.actor.*;
import com.poc.akka.config.akka.SpringExtension;
import com.poc.akka.dao.WorkflowDao;
import com.poc.akka.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import scala.concurrent.duration.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author by Pritom Gogoi
 */
@Component("createRecordActor")
@Scope("prototype")
public class CreateRecordActor extends UntypedActor {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private WorkflowDao workflowDao;

    @Override
    public void preStart() throws Exception {
        context().setReceiveTimeout(Duration.create(3, TimeUnit.MINUTES));
    }

    @Override
    public void onReceive(final Object message) throws Exception {
        if (message instanceof Workflow) {
            workflowDao.saveWorkflow((Workflow) message);
        } else if (message instanceof ReceiveTimeout) {
            context().stop(self());
        } else if (message instanceof String) {
            startErrorLogger((String) message, "Error happened while trying to send money to user, shutting down transferer actor");
            sender().tell(PoisonPill.getInstance(), self());
        } else {
            unhandled(message);
        }
    }

    private void startErrorLogger(final String userId,final String message) {
        final ActorRef errorHandler = context().actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("ErrorHandler"), "ERROR-FOR-".concat(userId));
        final Map<String, String> error = new HashMap<>();
        error.put("userId", userId);
        error.put("message", message);
        errorHandler.tell(error, self());
    }

}
