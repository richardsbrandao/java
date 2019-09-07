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
        performOrderOne()
        performOrderTwo()
    }

    private fun logPersistedOrder(orderService: OrderService, id: UUID) {
        val order = orderService.findById(id)
        log.info("** Current persisted order: $order")
    }

    fun performOrderOne() {
        val fridgeOrder1 = OrderItem(product = Product(name="Kühlschrank", price = 600), quantity = 1)
        val order: Order = orderService.open(fridgeOrder1)
        logPersistedOrder(orderService, order.id)

        val stoveOrder1 = OrderItem(product = Product(name="Herd", price = 300), quantity = 1)
        orderService.addItem(id = order.id, item = stoveOrder1)
        logPersistedOrder(orderService, order.id)

        orderService.checkout(id = order.id)
        logPersistedOrder(orderService, order.id)

        orderService.addDeliveryInfo(id = order.id, address = Address(address = "nowhere"))
        logPersistedOrder(orderService, order.id)

        orderService.confirm(id = order.id)
        logPersistedOrder(orderService, order.id)

        orderService.pay(id = order.id, amount = 900)
        logPersistedOrder(orderService, order.id)
    }

    fun performOrderTwo() {
        val microwaveOrderTwo = OrderItem(product = Product(name="Mikrowelle", price = 150), quantity = 1)
        val order: Order = orderService.open(microwaveOrderTwo)
        logPersistedOrder(orderService, order.id)

        orderService.checkout(id = order.id)
        logPersistedOrder(orderService, order.id)

        orderService.addDeliveryInfo(id = order.id, address = Address(address = "nowhere"))
        logPersistedOrder(orderService, order.id)

        orderService.confirm(id = order.id)
        logPersistedOrder(orderService, order.id)

        orderService.pay(id = order.id, amount = 150)
        logPersistedOrder(orderService, order.id)
    }
}

fun main(args: Array<String>) {
    runApplication<BezahlenApplicationRunner>(*args)
}
