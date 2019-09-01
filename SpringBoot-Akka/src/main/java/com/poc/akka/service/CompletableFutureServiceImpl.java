package com.poc.akka.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.poc.akka.config.akka.SpringExtension;
import com.poc.akka.message.GetMessage;
import com.poc.akka.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author by Pritom Gogoi
 */
@Service
public class CompletableFutureServiceImpl implements CompletableFutureService {

    @Autowired
    private ActorSystem actorSystem;

    @Override
    public CompletableFuture<Workflow> getWorkflowByDomainAndName(final String domainName, final String workflowName) {
        final CompletableFuture<Workflow> completableFuture = new CompletableFuture<>();
        final ActorRef processor = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("readDataActor"));
        processor.tell(new GetMessage(domainName,workflowName),null);
        return completableFuture;
    }
}
