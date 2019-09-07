package com.richard.statemachine.bezahlen.services

import com.richard.statemachine.bezahlen.config.action.LogStateChangeAction
import com.richard.statemachine.bezahlen.config.message.OrderMessage
import com.richard.statemachine.bezahlen.errors.PerformPaymentException
import com.richard.statemachine.bezahlen.models.Address
import com.richard.statemachine.bezahlen.models.Order
import com.richard.statemachine.bezahlen.models.OrderEvent
import com.richard.statemachine.bezahlen.models.OrderItem
import com.richard.statemachine.bezahlen.models.OrderState
import com.richard.statemachine.bezahlen.models.Payment
import com.richard.statemachine.bezahlen.models.PaymentStatus
import com.richard.statemachine.bezahlen.repositories.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val stateMachineService: StateMachineService,
    private val paymentService: PaymentService
) {
    private val log = LoggerFactory.getLogger(LogStateChangeAction::class.java)

    fun open(item: OrderItem): Order {
        log.info(">> Opening order with item $item")
        return orderRepository.create(Order(id = UUID.randomUUID(), items = mutableListOf(item)))
    }

    fun addItem(id: UUID, item: OrderItem) {
        log.info(">> Adding item $item into order $id")
        val order = findById(id)

        if(order.state != OrderState.CART) {
            throw RuntimeException("Order is already checked out")
        }

        order.items.add(item)
        orderRepository.save(order)
    }

    fun checkout(id: UUID) {
        log.info(">> Checking out order $id")
        val order = findById(id)
        val stateMachine = stateMachineService.create(order)
        stateMachine.sendEvent(OrderMessage().create(OrderEvent.CHECKOUT, order))
    }


    fun addDeliveryInfo(id: UUID, address: Address) {
        log.info(">> Adding delivery info $address into order $id")
        val order = findById(id)
        order.address = address
        val stateMachine = stateMachineService.create(order)
        stateMachine.sendEvent(OrderMessage().create(OrderEvent.ADD_DELIVERY_INFO, order))
    }

    fun confirm(id: UUID) {
        log.info(">> Confirming order $id")
        val order = findById(id)
        val stateMachine = stateMachineService.create(order)
        stateMachine.sendEvent(OrderMessage().create(OrderEvent.CONFIRM, order))
    }

    fun pay(id: UUID, amount: Int) {
        log.info(">> Request payment of $amount euros to roder $id")
        val order = findById(id)
        val stateMachine = stateMachineService.create(order)
        val payment = Payment(amount)

        try {
            paymentService.performPayment(payment)
            order.payment = Payment(amount, PaymentStatus.CONFIRMED)
            log.info(">> Accepting payment ${order.payment} for order $id")
            stateMachine.sendEvent(OrderMessage().create(OrderEvent.CONFIRM_PAYMENT, order))
        } catch (e: PerformPaymentException) {
            order.payment = Payment(amount, PaymentStatus.DENIED)
            log.info(">> Denying payment ${order.payment} for order $id")
            stateMachine.sendEvent(OrderMessage().create(OrderEvent.DENY_PAYMENT, order))
        }

    }

    fun findById(id: UUID): Order {
        return Optional.ofNullable(orderRepository.findById(id))
            .orElseThrow { RuntimeException("Order $id not found") }
    }
}
