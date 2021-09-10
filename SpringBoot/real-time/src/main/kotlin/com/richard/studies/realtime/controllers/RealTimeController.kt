package com.richard.studies.realtime.controllers

import com.richard.studies.realtime.controllers.mapper.RealTimeMessageMapper
import com.richard.studies.realtime.controllers.request.RealTimeMessageDto
import com.richard.studies.realtime.controllers.response.MatchRealTimeEventsDto
import com.richard.studies.realtime.models.RealTimeMessage
import com.richard.studies.realtime.services.RealTimeService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("matches/{matchId}/broadcast")
class RealTimeController(
    private val realTimeService: RealTimeService,
    private val realTimeMessageMapper: RealTimeMessageMapper

) {
    @PostMapping
    fun broadcast(@PathVariable("matchId") matchId: String, @RequestBody realTimeMessageDto: RealTimeMessageDto): Mono<Unit> {
        return Mono.just(realTimeMessageMapper.toRealTimeMessage(realTimeMessageDto))
            .flatMap { realTimeService.broadcast(matchId, it) }
            .flatMap { Mono.empty<Unit>() }
    }
}
