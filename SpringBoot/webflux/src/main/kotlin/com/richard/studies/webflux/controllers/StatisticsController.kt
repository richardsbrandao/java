package com.richard.studies.webflux.controllers

import com.richard.studies.webflux.services.StatisticsRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatisticsController(private val statisticsRepository: StatisticsRepository) {
    @GetMapping
    fun findAll(): Map<String, Int> {
        return statisticsRepository.findAll()
    }
}
