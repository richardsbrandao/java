package com.richard.studies.realtime.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("league")
data class League(
    @Id
    var id: UUID? = null,
    var country: String,
    var division: String,
    var season: String//,
//    @Transient
//    var clubs: List<Club> = emptyList(),
//    @Transient
//    var matches: List<Match> = emptyList()
)
