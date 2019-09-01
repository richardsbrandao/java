package com.poc.akka.config.aws;

/**
 * @author by Pritom Gogoi
 */

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SuppressWarnings("PMD.BeanMembersShouldSerialize")
@Configuration
public class AwsConfig {
    @Bean
    public AmazonDynamoDBClient getDynamoDBClient()  {
        return new AmazonDynamoDBClient(new AWSCredentialsProviderChain(new InstanceProfileCredentialsProvider(),
                new ProfileCredentialsProvider()));
    }
    @Bean
    public DynamoDBMapper getDynamoDBMapper()  {
        return new DynamoDBMapper(new AmazonDynamoDBClient(new AWSCredentialsProviderChain(new InstanceProfileCredentialsProvider(),
                new ProfileCredentialsProvider())));
    }
}