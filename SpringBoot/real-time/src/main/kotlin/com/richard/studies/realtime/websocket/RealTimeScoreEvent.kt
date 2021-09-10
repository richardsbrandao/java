package com.richard.studies.realtime.websocket

import org.springframework.context.ApplicationEvent

data class RealTimeScoreEvent(val realTimeEventSource: RealTimeEventSource): ApplicationEvent(realTimeEventSource)
