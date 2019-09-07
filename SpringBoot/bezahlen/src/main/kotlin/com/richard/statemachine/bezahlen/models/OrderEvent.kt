package com.richard.statemachine.bezahlen.models

enum class OrderEvent {
    CHECKOUT,
    ADD_DELIVERY_INFO,
    CONFIRM,
    CONFIRM_PAYMENT,
    DENY_PAYMENT
}
