package com.richard.studies.realtime.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("club")
data class Club(
    @Id
    var id: UUID? = null,
    val name: String,
    val country: String,
    val image: String
)
