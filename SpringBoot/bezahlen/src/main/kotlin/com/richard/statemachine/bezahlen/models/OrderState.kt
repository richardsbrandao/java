package com.richard.statemachine.bezahlen.models

enum class OrderState {
    CART,
    LOG_IN,
    DELIVERY_INFO,
    CONFIRMATION,
    PAYMENT_CONFIRMED,

    GAVE_UP_ON_LOG_IN,
    GAVE_UP_ON_DELIVERY_INFO,
    GAVE_UP_ON_CONFIRMATION,
    PAYMENT_DENIED,
}
