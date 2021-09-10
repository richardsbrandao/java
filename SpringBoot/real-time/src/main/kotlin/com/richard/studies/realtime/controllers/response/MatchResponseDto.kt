package com.richard.studies.realtime.controllers.response

import java.time.Instant

data class MatchResponseDto(
    val id: String,
    val leagueName: String,
    val homeClubName: String,
    val awayClubName: String,
    val stadium: String,
    val date: Instant,
    val matchRealTimeEvents: List<MatchRealTimeEventsDto>
)
