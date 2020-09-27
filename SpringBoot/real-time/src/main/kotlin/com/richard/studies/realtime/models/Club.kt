package com.richard.studies.realtime.models

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Club(
    var id: String? = null,
    val name: String,
    val country: String,
    val image: String
)
