package com.poc.akka.service;

import com.poc.akka.model.Workflow;

import java.util.concurrent.CompletableFuture;

/**
 * @author by Pritom Gogoi
 */
public interface CompletableFutureService {

    CompletableFuture<Workflow> getWorkflowByDomainAndName(final String domain, final String name);

}
