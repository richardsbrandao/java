package com.richard.studies.webflux.services

import com.richard.studies.webflux.models.Country
import com.richard.studies.webflux.models.mappers.CountryMapper
import com.richard.studies.webflux.repositories.CountriesRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CountriesService(
    private val countriesRepository: CountriesRepository,
    private val statisticsRepository: StatisticsRepository,
    private val countryMapper: CountryMapper
) {
    fun findByName(name: String): Flux<Country> {
        return countriesRepository.findByName(name)
            .map(countryMapper::toModel)
            .doOnNext { statisticsRepository.add(it.name) }
    }

    fun findAll(): Flux<Country> {
        return countriesRepository.findAll()
            .map(countryMapper::toModel)
    }
}

//fun main() {
//    val countriesApiClient = ApiClientsConfiguration().countriesApiClient()
//    val countryMapper = CountryMapper()
//    val repository = CountriesRepository(countriesApiClient)
//
//    val result = repository.findByName("brazil")
//        .map(countryMapper::toModel)
//        .map { country ->
////            country.borders.map { alphaCode -> repository.findByAlphaCode(alphaCode) }
////            Country(
////                name = country.name,
////                ddi = country.ddi,
////                capital = country.capital,
////                region = country.region,
////                population = country.population,
////                codes = country.codes,
////                languages = country.languages,
////                borders = country.borders,
////                bordersComplete = country.borders.map { alphaCode -> repository.findByAlphaCode(alphaCode). }
////            )
////            country.borders.map { alphaCode -> repository.findByAlphaCode(alphaCode) }
//
//        }
////        .map { it.bordersComplete }
//        .subscribe { println(it) }
//
//    println(result)
//    sleep(2000)
//
//    countriesRepository.findByName(name)
//        .map(countryMapper::toModel)
//        .map { country ->
//            country.borders.map { alphaCode ->
//                countriesRepository.findByAlphaCode(alphaCode)
//                    .map { country -> countryMapper.toModel(country) }
////                        .collect { country.bordersComplete?.add(it) }
//            }
//            country
//        }
//}
