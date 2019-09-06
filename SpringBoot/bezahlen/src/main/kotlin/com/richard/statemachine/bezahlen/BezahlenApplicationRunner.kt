package com.richard.statemachine.bezahlen

import com.richard.statemachine.bezahlen.models.Address
import com.richard.statemachine.bezahlen.models.Order
import com.richard.statemachine.bezahlen.models.OrderEvent
import com.richard.statemachine.bezahlen.models.OrderItem
import com.richard.statemachine.bezahlen.models.OrderState
import com.richard.statemachine.bezahlen.models.Product
import com.richard.statemachine.bezahlen.services.OrderService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.statemachine.config.StateMachineFactory
import java.util.*

@SpringBootApplication
class BezahlenApplicationRunner(
    private val orderService: OrderService
) {
    private val log = LoggerFactory.getLogger(BezahlenApplication::class.java)

    @Bean
    fun init(stateMachineFactory: StateMachineFactory<OrderState, OrderEvent>) = CommandLineRunner {
        val fridgeOrder1 = OrderItem(product = Product(name="Kühlschrank", price = 600), quantity = 1)
        val order: Order = orderService.open(fridgeOrder1)
        logPersistedOrder(orderService, order.id)
//        val stoveOrder1 = OrderItem(product = Product(name="Herd", price = 300), quantity = 1)
//        orderService.addItem(id = order.id, item = stoveOrder1)
//        logPersistedOrder(orderService, order.id)

        orderService.checkout(id = order.id)
        logPersistedOrder(orderService, order.id)

        orderService.addDeliveryInfo(id = order.id, address = Address(address = "nowhere"))
        logPersistedOrder(orderService, order.id)

        orderService.confirm(id = order.id)
        logPersistedOrder(orderService, order.id)

        orderService.pay(id = order.id, amount = 900)
        logPersistedOrder(orderService, order.id)

//        val orderIdHeader = "orderId"
//        val orderIdOne = "ID_1"
//        val orderIdTwo = "ID_2"
//
//        val stateMachineOrder1 = stateMachineFactory.getStateMachine(orderIdOne)
//        val stateMachineOrder2 = stateMachineFactory.getStateMachine(orderIdTwo)
//
//        stateMachineOrder1.extendedState.variables[orderIdHeader] = orderIdOne
//        stateMachineOrder2.extendedState.variables[orderIdHeader] = orderIdTwo
//
//        stateMachineOrder1.start()
//        log.info("StateMachine1: ${stateMachineOrder1.state.id}")
//
//        stateMachineOrder2.start()
//        log.info("StateMachine2: ${stateMachineOrder2.state.id}")
//
//        stateMachineOrder1.sendEvent(OrderEvent.CHECKOUT)
//        log.info("StateMachine1: ${stateMachineOrder1.state.id}")
//
//        stateMachineOrder1.sendEvent(
//            MessageBuilder
//                .withPayload(OrderEvent.ADD_DELIVERY_INFO)
//                .build()
//        )
//        log.info("StateMachine1: ${stateMachineOrder1.state.id}")
//
//        stateMachineOrder2.sendEvent(
//            MessageBuilder
//                .withPayload(OrderEvent.CHECKOUT)
//                .build()
//        )
//        log.info("StateMachine2: ${stateMachineOrder2.state.id}")
    }

    private fun logPersistedOrder(orderService: OrderService, id: UUID) {
        val order = orderService.findById(id)
        log.info("CurrentOrder: $order")
    }
}

fun main(args: Array<String>) {
    runApplication<BezahlenApplicationRunner>(*args)
}
