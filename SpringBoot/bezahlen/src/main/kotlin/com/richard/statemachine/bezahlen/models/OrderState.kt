package com.richard.statemachine.bezahlen.models

enum class OrderState {
    CART,
    CHECKED_OUT,
    DELIVERY_INFO,
    CONFIRMATION,
    PAYMENT_CONFIRMED,

    GAVE_UP_ON_CART,
    GAVE_UP_ON_CHECK_OUT,
    GAVE_UP_ON_DELIVERY_INFO,
    GAVE_UP_ON_CONFIRMATION,
    PAYMENT_DENIED,
}
