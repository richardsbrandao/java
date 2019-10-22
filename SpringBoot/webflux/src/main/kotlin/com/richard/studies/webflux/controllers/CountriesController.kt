package com.richard.studies.webflux.controllers

import com.richard.studies.webflux.models.Country
import com.richard.studies.webflux.services.CountriesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class CountriesController(private val countriesService: CountriesService) {
    @GetMapping("/countries/{name}")
    fun findByName(@PathVariable("name") name: String): Flux<Country> {
        return countriesService.findByName(name)
    }

    @GetMapping("/countries")
    fun findAll(): Flux<Country> {
        return countriesService.findAll()
    }

}
