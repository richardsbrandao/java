package com.richard.statemachine.bezahlen.models

data class Order(
    val id: Int,
    val items: OrderItem,
    val state: OrderState
)
