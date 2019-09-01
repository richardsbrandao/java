package com.poc.akka.dao;

import com.poc.akka.model.Workflow;

/**
 * @author by Pritom Gogoi
 */
public interface WorkflowDao {

    void saveWorkflow(final Workflow workflow);
    Workflow getWorkflowByDomainAndName(final String domain, final String name);
}
