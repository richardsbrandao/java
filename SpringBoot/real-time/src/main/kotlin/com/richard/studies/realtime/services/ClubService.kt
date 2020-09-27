package com.richard.studies.realtime.services

import com.richard.studies.realtime.models.Club
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

@Service
class ClubService(private val leagueService: LeagueService) {
    fun save(club: Club, leagueId: String): Mono<Club> {
        return leagueService.findById(leagueId)
                .flatMap { league ->
                    club.id = UUID.randomUUID().toString()
                    league.clubs.add(club)
                    leagueService.save(league)
                            .then(Mono.just(club))
                }
    }
}
