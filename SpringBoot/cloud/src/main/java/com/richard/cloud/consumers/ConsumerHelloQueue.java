package com.richard.cloud.consumers;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConsumerHelloQueue {

    @Autowired
    private AmazonSQS sqsClient;

    @Value("${cloud.aws.sqs.hello_world}")
    private String queueUrl;


    @Scheduled(fixedRate = 1000)
    public void consume() {
        ReceiveMessageResult result = sqsClient.receiveMessage(queueUrl);

        if(!result.getMessages().isEmpty()) {
            sqsClient.deleteMessage(queueUrl, result.getMessages().get(0).getReceiptHandle());
            System.out.println(result.getMessages());
        }
    }

}
