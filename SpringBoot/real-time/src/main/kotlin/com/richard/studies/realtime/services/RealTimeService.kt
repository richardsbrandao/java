package com.richard.studies.realtime.services

import com.richard.studies.realtime.models.Match
import com.richard.studies.realtime.models.RealTimeMessage
import com.richard.studies.realtime.models.RealTimeMessageType
import com.richard.studies.realtime.models.Score
import com.richard.studies.realtime.models.Team
import com.richard.studies.realtime.websocket.RealTimeEventSource
import com.richard.studies.realtime.websocket.RealTimeScoreEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class RealTimeService(
    private val matchService: MatchService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun broadcast(matchId: String, realTimeMessage: RealTimeMessage): Mono<Match> {
        return matchService.findById(matchId)
            .map {
                it.realTimeMessages.add(realTimeMessage)
                when(realTimeMessage.type) {
                    RealTimeMessageType.FIRST_HALF_START -> it.score = Score(home = 0, away = 0)
                    RealTimeMessageType.GOAL -> it.score = Score(
                        home = if (realTimeMessage.team == Team.HOME) it.score!!.home+1 else it.score!!.home,
                        away = if (realTimeMessage.team == Team.AWAY) it.score!!.away+1 else it.score!!.away
                    )
                }
                it
            }
            .flatMap(matchService::save)
            .doOnSuccess { match ->
                println("Enviando $match")
                applicationEventPublisher.publishEvent(RealTimeScoreEvent(RealTimeEventSource(score = match.score!!, realTimeMessage = realTimeMessage)))
            }
    }
}
