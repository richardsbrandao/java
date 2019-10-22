package com.richard.studies.webflux

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ApiClientsConfiguration {
    @Bean
    fun countriesApiClient() : WebClient {
        return WebClient.builder()
            .baseUrl("https://restcountries.eu/rest/v2")
            .build()
    }
}
