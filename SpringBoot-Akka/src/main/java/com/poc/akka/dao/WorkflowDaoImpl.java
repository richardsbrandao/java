package com.poc.akka.dao;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.poc.akka.config.aws.DynamoDBConfig;
import com.poc.akka.model.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @author by Pritom Gogoi
 */
@Repository
public class WorkflowDaoImpl implements WorkflowDao {

    private static final Logger LOG = LoggerFactory.getLogger(WorkflowDaoImpl.class);

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private DynamoDBConfig dynamoDBConfig;

    @PostConstruct
    public void setup() {
        dynamoDBConfig.createTable(Workflow.class, 5, 5);
    }

    @Override
    public void saveWorkflow(final Workflow workflow) {
        try {
            dynamoDBMapper.save(workflow);
        } catch (AmazonClientException e) {
            LOG.error("Error in saving the record to dynamo : " + e.getMessage(), e);
        }
    }

    @Override
    public Workflow getWorkflowByDomainAndName(final String domainName,final String workflowName) {
        return dynamoDBMapper.load(Workflow.class, domainName, workflowName);
    }
}