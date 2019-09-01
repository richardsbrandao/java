package com.poc.akka.service;

import com.poc.akka.model.Workflow;

/**
 * @author by Pritom Gogoi
 */
public interface WorkflowService {

    void startDynamoWorkerActor(final Workflow workflow);
    Workflow getWorkflowByDomainNameAndWorkflowName(final String domainName, final String workflowName);
}
