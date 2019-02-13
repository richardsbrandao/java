package com.richard.dynkot

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException
import com.richard.dynkot.entities.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import kotlin.reflect.KClass

@Configuration
@EnableDynamoDBRepositories
class DynamoDbConfiguration {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Profile("development")
    @Bean("amazonDynamoDB")
    fun dynamoDb(
            @Value("\${dynamodb.credentials.profile}") awsProfile : String,
            @Value("\${dynamodb.credentials.region}") awsRegion : String
    ) : AmazonDynamoDB {
        val client : AmazonDynamoDB = AmazonDynamoDBClientBuilder
                    .standard()
                    .withCredentials(ProfileCredentialsProvider(awsProfile))
                    .withRegion(awsRegion)
                    .build()
        DynamoDB(client).getTable("dev-user").waitForActive()
        return client
    }

    @Profile("test")
    @Bean("amazonDynamoDB")
    fun dynamoDbTest() : AmazonDynamoDB {
        val client : AmazonDynamoDB = AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-east-1"))
                .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials("key", "secret")))
                .build()

        createTableForEntity(client, User::class)

        DynamoDB(client).getTable("dev-user").waitForActive()
        return client
    }

    private fun createTableForEntity(amazonDynamoDB: AmazonDynamoDB, entity: KClass<*>) {

        val tableRequest = DynamoDBMapper(amazonDynamoDB)
                .generateCreateTableRequest(entity.java)
                .withProvisionedThroughput(ProvisionedThroughput(1L, 1L))
        // create table properly
        try {
            DynamoDB(amazonDynamoDB).createTable(tableRequest)
            log.info("Table created! [entity={}]", entity)
        } catch (e: ResourceInUseException) {
            log.info("Table already exists - skip creation! [entity={}]", entity)
        }
    }
}