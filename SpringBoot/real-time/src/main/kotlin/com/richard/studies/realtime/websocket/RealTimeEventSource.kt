package com.richard.studies.realtime.websocket

import com.richard.studies.realtime.models.RealTimeMessage
import com.richard.studies.realtime.models.Score

data class RealTimeEventSource(
    val score: Score,
    val realTimeMessage: RealTimeMessage
)
