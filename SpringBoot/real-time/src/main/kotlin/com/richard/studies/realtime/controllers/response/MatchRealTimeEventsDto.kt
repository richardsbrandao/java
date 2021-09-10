package com.richard.studies.realtime.controllers.response

import com.richard.studies.realtime.models.RealTimeMessageType
import com.richard.studies.realtime.models.Team

data class MatchRealTimeEventsDto(
    val time: String,
    val text: String,
    val type: RealTimeMessageType,
    val team: Team
)
