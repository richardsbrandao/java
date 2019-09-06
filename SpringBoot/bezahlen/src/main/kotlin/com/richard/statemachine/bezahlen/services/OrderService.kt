package com.richard.statemachine.bezahlen.services

import com.richard.statemachine.bezahlen.config.interceptors.UpdateOrderService
import com.richard.statemachine.bezahlen.config.message.OrderMessage
import com.richard.statemachine.bezahlen.models.Address
import com.richard.statemachine.bezahlen.models.Order
import com.richard.statemachine.bezahlen.models.OrderEvent
import com.richard.statemachine.bezahlen.models.OrderItem
import com.richard.statemachine.bezahlen.models.OrderState
import com.richard.statemachine.bezahlen.repositories.OrderRepository
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val stateMachineService: StateMachineService
) {
    fun open(item: OrderItem): Order {
        return orderRepository.create(Order(id = UUID.randomUUID(), items = mutableListOf(item)))
    }

    fun addItem(id: UUID, item: OrderItem) {

    }

    fun checkout(id: UUID) {
        val order = orderRepository.findById(id)
        val stateMachine = stateMachineService.create(order!!)
        stateMachine.sendEvent(OrderMessage().create(OrderEvent.CHECKOUT, order))
    }


    fun addDeliveryInfo(id: UUID, address: Address) {
        val order = orderRepository.findById(id)
        order?.address = address
        val stateMachine = stateMachineService.create(order!!)
        stateMachine.sendEvent(OrderMessage().create(OrderEvent.ADD_DELIVERY_INFO, order))
    }

    fun confirm(id: UUID) {

    }

    fun pay(id: UUID, amount: Int) {

    }

    fun findById(id: UUID): Order? {
        return orderRepository.findById(id)
    }
}
