package com.richard.studies.realtime.controllers.mappers

import com.richard.studies.realtime.controllers.requests.CreateLeagueRequest
import com.richard.studies.realtime.controllers.responses.CreateLeagueResponse
import com.richard.studies.realtime.models.League
import org.springframework.stereotype.Component

@Component
class LeagueMapper {
    fun toLeague(createLeagueRequest: CreateLeagueRequest): League {
        return League(
            country = createLeagueRequest.country!!,
            division = createLeagueRequest.division!!,
            season = createLeagueRequest.season!!
        )
    }

    fun toCreateLeagueResponse(league: League): CreateLeagueResponse {
        return CreateLeagueResponse(id = league.id!!)
    }

    fun toLeagueResponse(league: League): LeagueResponse {
        return LeagueResponse(
            id = league.id!!,
            country = league.country,
            season = league.season,
            division = league.division
        )
    }
}
