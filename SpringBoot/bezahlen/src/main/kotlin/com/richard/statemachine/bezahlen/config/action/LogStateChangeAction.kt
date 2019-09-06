package com.richard.statemachine.bezahlen.config.action

import com.richard.statemachine.bezahlen.config.message.MessageMetadata
import com.richard.statemachine.bezahlen.models.OrderEvent
import com.richard.statemachine.bezahlen.models.OrderState
import org.slf4j.LoggerFactory
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action

class LogStateChangeAction : Action<OrderState, OrderEvent> {
    private val log = LoggerFactory.getLogger(LogStateChangeAction::class.java)

    override fun execute(context: StateContext<OrderState, OrderEvent>) {
        val orderId = context.extendedState.variables[MessageMetadata.ORDER_ID_HEADER]
        val event = context.message.payload
        log.info("Event $event for OrderId $orderId from ${context.source.id} state to ${context.target.id}")
    }
}
