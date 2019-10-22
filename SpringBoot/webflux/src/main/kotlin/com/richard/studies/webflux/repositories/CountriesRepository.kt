package com.richard.studies.webflux.repositories

import com.richard.studies.webflux.repositories.dto.CountryApiDto
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class CountriesRepository(private val countriesApiClient: WebClient) {
    fun findByName(name: String): Flux<CountryApiDto> {
        return countriesApiClient.get()
            .uri("/name/$name")
            .retrieve()
            .bodyToFlux(CountryApiDto::class.java)
    }

    fun findAll(): Flux<CountryApiDto> {
        return countriesApiClient.get()
            .uri("/all")
            .retrieve()
            .bodyToFlux(CountryApiDto::class.java)
    }

    fun findByAlphaCode(alphaCode: String): Mono<CountryApiDto> {
        return countriesApiClient.get()
            .uri("/alpha/${alphaCode.toLowerCase()}")
            .retrieve()
            .bodyToMono(CountryApiDto::class.java)
    }
}
