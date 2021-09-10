package com.richard.studies.realtime.services

import com.richard.studies.realtime.models.Match
import com.richard.studies.realtime.repositories.MatchRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MatchService(
    private val matchRepository: MatchRepository
) {
    fun save(match: Match): Mono<Match> = matchRepository.save(match)
    fun findById(id: String): Mono<Match> = matchRepository.findById(id)
    fun findAll(): Flux<Match> = matchRepository.findAll()
}
