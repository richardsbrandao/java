package com.poc.akka.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.poc.akka.config.akka.SpringExtension;
import com.poc.akka.message.GetMessage;
import com.poc.akka.model.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * @author by Pritom Gogoi
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

    private static final Logger LOG = LoggerFactory.getLogger(WorkflowServiceImpl.class);

    @Autowired
    private ActorSystem actorSystem;

    @Override
    public void startDynamoWorkerActor(final Workflow workflow) {
        final ActorRef processor = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("createRecordActor"));
        processor.tell(workflow, ActorRef.noSender());
    }

    @Override
    public Workflow getWorkflowByDomainNameAndWorkflowName(final String domainName,final  String workflowName) {
        final ActorRef actorRef = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("readActor"));
        final Timeout timeout = new Timeout(Duration.create(5, "seconds"));
        final Future<Object> future = Patterns.ask(actorRef, new GetMessage(domainName, workflowName), timeout);
        Workflow result = null;
        try {
            result = (Workflow) Await.result(future, timeout.duration());
        } catch (Exception e) {
          LOG.error(e.getMessage(),e);
        }

        return result;
    }
}
