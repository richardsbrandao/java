package com.richard.studies.realtime.models

import java.util.UUID

data class RealTime(
    val id: UUID,
    val time: String,
    val text: String,
    val type: RealTimeMessageType,
    val club: Club? = null
)
