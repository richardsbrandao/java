package com.richard.studies.realtime.models

data class RealTimeMessage(
    val time: String,
    val text: String,
    val type: RealTimeMessageType,
    val team: Team
)
