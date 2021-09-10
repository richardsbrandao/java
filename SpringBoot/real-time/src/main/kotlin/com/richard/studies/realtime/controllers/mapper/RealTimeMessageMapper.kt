package com.richard.studies.realtime.controllers.mapper

import com.richard.studies.realtime.controllers.request.RealTimeMessageDto
import com.richard.studies.realtime.models.RealTimeMessage
import org.springframework.stereotype.Component

@Component
class RealTimeMessageMapper {
    fun toRealTimeMessage(realTimeMessageDto: RealTimeMessageDto): RealTimeMessage {
        return RealTimeMessage(
            time = realTimeMessageDto.time,
            type = realTimeMessageDto.type,
            team = realTimeMessageDto.team,
            text = realTimeMessageDto.text
        )
    }

}
