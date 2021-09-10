package com.richard.studies.realtime.controllers.request

import com.richard.studies.realtime.models.Score
import java.time.Instant

data class CreateMatchRequestDto(
    val leagueName: String,
    val homeClubName: String,
    val awayClubName: String,
    val stadium: String,
    val date: Instant
)
