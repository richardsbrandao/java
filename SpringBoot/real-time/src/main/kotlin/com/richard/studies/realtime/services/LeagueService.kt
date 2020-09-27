package com.richard.studies.realtime.services

import com.richard.studies.realtime.repositories.LeagueRepository
import com.richard.studies.realtime.models.League
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class LeagueService(private val leagueRepository: LeagueRepository) {
    fun save(league: League): Mono<League> {
        return leagueRepository.save(league)
    }

    fun findById(leagueId: String): Mono<League> {
        return leagueRepository.findById(leagueId)
    }
}
