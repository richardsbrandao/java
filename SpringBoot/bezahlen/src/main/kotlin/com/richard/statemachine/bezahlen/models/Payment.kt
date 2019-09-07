package com.richard.statemachine.bezahlen.models

data class Payment(val amount: Int, var status: PaymentStatus = PaymentStatus.REQUESTED)
