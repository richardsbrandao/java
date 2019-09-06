package com.richard.statemachine.bezahlen.services

import com.richard.statemachine.bezahlen.config.interceptors.UpdateOrderService
import com.richard.statemachine.bezahlen.models.Order
import com.richard.statemachine.bezahlen.models.OrderEvent
import com.richard.statemachine.bezahlen.models.OrderState
import com.richard.statemachine.bezahlen.repositories.OrderRepository
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineFactory
import org.springframework.statemachine.support.DefaultStateMachineContext
import org.springframework.stereotype.Service

@Service
class StateMachineService(
    private val factory: StateMachineFactory<OrderState, OrderEvent>,
    private val orderRepository: OrderRepository
) {
    fun create(order: Order): StateMachine<OrderState, OrderEvent> {
        val stateMachine = factory.getStateMachine(order.id)
        stateMachine.stop()
        stateMachine.stateMachineAccessor.doWithAllRegions {
            stm ->
            stm.addStateMachineInterceptor(UpdateOrderService(orderRepository))
            stm.resetStateMachine(DefaultStateMachineContext(order.state, null, null, null))
        }
        stateMachine.start()
        return stateMachine
    }

}
