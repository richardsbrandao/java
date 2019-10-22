package com.richard.studies.webflux.models.mappers

import com.richard.studies.webflux.models.Country
import com.richard.studies.webflux.repositories.dto.CountryApiDto
import org.springframework.stereotype.Component

@Component
class CountryMapper {
    fun toModel(countryApiDto: CountryApiDto): Country {
        return Country(
            name = countryApiDto.name,
            ddi = countryApiDto.callingCodes,
            capital = countryApiDto.capital,
            region = countryApiDto.region,
            population = countryApiDto.population,
            codes = listOf(countryApiDto.alpha2Code, countryApiDto.alpha3Code),
            languages = countryApiDto.languages,
            borders = countryApiDto.borders
        )
    }
}
