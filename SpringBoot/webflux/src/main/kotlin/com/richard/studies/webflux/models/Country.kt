package com.richard.studies.webflux.models

import com.richard.studies.webflux.repositories.dto.Language

data class Country(
    val name: String,
    val ddi: List<String>,
    val capital: String,
    val region: String,
    val population: Int,
    val codes: List<String>,
    val languages: List<Language>,
    val borders: List<String>
)
