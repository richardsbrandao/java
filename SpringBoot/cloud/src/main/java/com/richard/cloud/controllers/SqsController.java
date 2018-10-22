package com.richard.cloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sqs")
public class SqsController {

    @Autowired
    private QueueMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<String> post(@RequestBody String json) {
        Message<String> message = MessageBuilder.withPayload(json).build();
        messagingTemplate.send("hello_world_queue", message);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
