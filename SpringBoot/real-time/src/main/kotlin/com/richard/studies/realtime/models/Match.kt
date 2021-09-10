package com.richard.studies.realtime.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.time.OffsetDateTime

@Document
data class Match(
    @Id
    val id: String? = null,
    val leagueName: String,
    val homeClubName: String,
    val awayClubName: String,
    var score: Score? = null,
    val stadium: String,
    val date: Instant,
    val realTimeMessages: MutableList<RealTimeMessage> = mutableListOf()
)
