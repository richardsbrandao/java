package com.richard.studies.realtime.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class League(
    @Id
    var id: String? = null,
    var country: String,
    var division: String,
    var season: String,
    var clubs: MutableSet<Club> = mutableSetOf()
)
