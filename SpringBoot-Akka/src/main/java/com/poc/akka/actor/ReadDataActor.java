package com.poc.akka.actor;

import akka.actor.UntypedActor;
import com.poc.akka.dao.WorkflowDao;
import com.poc.akka.message.GetMessage;
import com.poc.akka.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import scala.concurrent.duration.Duration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author by Pritom Gogoi
 */
@Component("readDataActor")
@Scope("prototype")
public class ReadDataActor extends UntypedActor {

    @Autowired
    private WorkflowDao workflowDao;

    @Override
    public void preStart() throws Exception {
        context().setReceiveTimeout(Duration.create(3, TimeUnit.MINUTES));
    }

    @Override
    public void onReceive(final Object message) throws Exception {

        if (message instanceof GetMessage) {
            final Workflow workflow = workflowDao.getWorkflowByDomainAndName(((GetMessage) message).getDomainName(),
                    ((GetMessage) message).getWorkflowName());
            CompletableFuture.completedFuture(workflow);
        } else {
            unhandled(message);
        }
        getContext().stop(self());
    }


}
