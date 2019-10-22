package com.richard.studies.webflux.repositories.dto

data class CountryApiDto(
    val name: String,
    val callingCodes: List<String>,
    val capital: String,
    val region: String,
    val population: Int,
    val alpha2Code: String,
    val alpha3Code: String,
    val languages: List<Language>,
    val borders: List<String>
)
