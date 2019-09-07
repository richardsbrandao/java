package com.richard.statemachine.bezahlen.config.interceptors

import com.richard.statemachine.bezahlen.BezahlenApplication
import com.richard.statemachine.bezahlen.models.Order
import com.richard.statemachine.bezahlen.models.OrderEvent
import com.richard.statemachine.bezahlen.models.OrderState
import com.richard.statemachine.bezahlen.repositories.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.state.State
import org.springframework.statemachine.support.StateMachineInterceptorAdapter
import org.springframework.statemachine.transition.Transition
import org.springframework.stereotype.Component

@Component
class UpdateOrderService(
    private val orderRepository: OrderRepository
) : StateMachineInterceptorAdapter<OrderState, OrderEvent>() {
    private val log = LoggerFactory.getLogger(UpdateOrderService::class.java)

    override fun preStateChange(
        state: State<OrderState, OrderEvent>,
        message: Message<OrderEvent>,
        transition: Transition<OrderState, OrderEvent>,
        stateMachine: StateMachine<OrderState, OrderEvent>
    ) {
        val order= message.headers["order"] as Order
        order.state = state.id
        orderRepository.save(order)
        log.info("> Order updated $order")
    }
}
