package com.richard.cloud.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationSubject;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatus;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationMessageMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationSubscriptionMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationUnsubscribeConfirmationMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sns")
public class SnsController {
    @NotificationSubscriptionMapping
    public void confirmSubscriptionMessage(NotificationStatus notificationStatus) {
        System.out.println("confirmSubscriptionMessage");
        notificationStatus.confirmSubscription();
    }

    @NotificationMessageMapping
    public void receiveNotification(@NotificationMessage String message,
                                    @NotificationSubject String subject) {
        System.out.println(subject + " -> " + message);
    }

    @NotificationUnsubscribeConfirmationMapping
    public void confirmUnsubscribeMessage(NotificationStatus notificationStatus) {
        System.out.println("confirmUnsubscribeMessage");
        notificationStatus.confirmSubscription();
    }
}
