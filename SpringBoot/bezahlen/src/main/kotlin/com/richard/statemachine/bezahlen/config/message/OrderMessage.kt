package com.richard.statemachine.bezahlen.config.message

import com.richard.statemachine.bezahlen.models.Order
import com.richard.statemachine.bezahlen.models.OrderEvent
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder

class OrderMessage {
    fun create(event: OrderEvent, order: Order): Message<OrderEvent> {
        return MessageBuilder
                    .withPayload(event)
                    .setHeader(MessageMetadata.ORDER_HEADER, order)
                    .build()
    }
}
