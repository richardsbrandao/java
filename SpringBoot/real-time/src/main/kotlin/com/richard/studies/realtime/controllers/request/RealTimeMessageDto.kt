package com.richard.studies.realtime.controllers.request

import com.richard.studies.realtime.models.RealTimeMessageType
import com.richard.studies.realtime.models.Team

data class RealTimeMessageDto(
    val time: String,
    val text: String,
    val type: RealTimeMessageType,
    val team: Team
)
