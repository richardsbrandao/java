package com.richard.studies.realtime.models

import java.time.OffsetDateTime

data class Match(
    val league: League,
    val home: Club,
    val away: Club,
    val score: Score,
    val stadium: String,
    val date: OffsetDateTime
//    val realTime: RealTimeMatch
)
