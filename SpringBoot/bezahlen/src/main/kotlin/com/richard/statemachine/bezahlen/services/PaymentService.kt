package com.richard.statemachine.bezahlen.services

import com.richard.statemachine.bezahlen.errors.PerformPaymentException
import com.richard.statemachine.bezahlen.models.Payment
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PaymentService {
    private val log = LoggerFactory.getLogger(PaymentService::class.java)

    fun performPayment(payment: Payment) {
        if (payment.amount < 200) {
            throw PerformPaymentException()
        }

        log.info("Payment ${payment.amount} was performed!")
    }
}
