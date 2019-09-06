package com.richard.statemachine.bezahlen.repositories

import com.richard.statemachine.bezahlen.models.Order
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class OrderRepository {
    companion object {
        private val table: MutableMap<UUID, Order> = mutableMapOf()
    }

    fun findById(id: UUID): Order? {
        return OrderRepository.table[id]?.copy()
    }

    fun create(order: Order): Order {
        OrderRepository.table[order.id] = order
        return order.copy()
    }

    fun save(order: Order) {
        OrderRepository.table[order.id] = order
    }
}
