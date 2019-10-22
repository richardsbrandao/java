package com.richard.studies.webflux.services

import org.springframework.stereotype.Repository

@Repository
class StatisticsRepository {
    companion object {
        val access : MutableMap<String, Int> = mutableMapOf<String, Int>()
    }

    fun add(country: String) {
        access.putIfAbsent(country, 0)
        access.computeIfPresent(country) { _, v -> v + 1 }
    }

    fun findAll(): Map<String, Int> {
        return access
    }
}
