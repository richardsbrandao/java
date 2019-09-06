package com.richard.statemachine.bezahlen.models

import java.time.Instant
import java.util.*

data class Order(
    val id: UUID,
    val timestamp: Instant = Instant.now(),
    val items: List<OrderItem> = mutableListOf(),
    var state: OrderState = OrderState.CART,
    var address: Address? = null
)
