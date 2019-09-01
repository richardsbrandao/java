package com.poc.akka.config.aws;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author by Pritom Gogoi
 */
@Configuration
public class DynamoDBConfig {
    private static final Logger LOG = LoggerFactory.getLogger(DynamoDBConfig.class);
    @Autowired
    private AmazonDynamoDBClient client;
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public boolean createTable(final Class<?> clazz, final long readCapacityUnits, final long writeCapacityUnits) {
        final boolean result = true;
        final CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(clazz);
        createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(readCapacityUnits, writeCapacityUnits));
        final List<GlobalSecondaryIndex> lstGIndex = createTableRequest.getGlobalSecondaryIndexes();
        if ((lstGIndex != null) && !lstGIndex.isEmpty()) {
            for (final GlobalSecondaryIndex index : lstGIndex) {
                index.setProvisionedThroughput(new ProvisionedThroughput(readCapacityUnits, writeCapacityUnits));
            }
        }
        CreateTableResult createTableResult = null;
        LOG.info("Trying to create dynamoDB table: " + createTableRequest.getTableName());
        try {
            createTableResult = client.createTable(createTableRequest);
        } catch (final ResourceInUseException exp) {
            if (exp.getMessage().contains("Table already exists:")) {
                LOG.info("Table already Exists: " + createTableRequest.getTableName());
            }
        }
        if (null != createTableResult) {
            final String tableName = createTableRequest.getTableName();
            String tableStatus = createTableResult.getTableDescription().getTableStatus();
            while (!"ACTIVE".equals(tableStatus)) {
                /*delay*/
                LOG.info("Waiting for table " + tableName + " to be created...this may take a while... current status is " + createTableResult.getTableDescription().getTableStatus());
                tableStatus = client.describeTable(tableName).getTable().getTableStatus();
            }
            LOG.info("Table successfully created :" + tableName + " and current status is " + tableStatus);
        }
        return result;
    }
}