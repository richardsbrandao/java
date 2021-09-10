package com.richard.studies.realtime.controllers.mapper

import com.richard.studies.realtime.controllers.request.CreateMatchRequestDto
import com.richard.studies.realtime.controllers.request.RealTimeMessageDto
import com.richard.studies.realtime.controllers.response.CreateMatchResponseDto
import com.richard.studies.realtime.controllers.response.MatchRealTimeEventsDto
import com.richard.studies.realtime.controllers.response.MatchResponseDto
import com.richard.studies.realtime.models.Match
import org.springframework.stereotype.Component

@Component
class MatchMapper {
    fun toMatch(createMatchRequestDto: CreateMatchRequestDto): Match = Match(
        leagueName = createMatchRequestDto.leagueName,
        awayClubName = createMatchRequestDto.awayClubName,
        homeClubName = createMatchRequestDto.homeClubName,
        date = createMatchRequestDto.date,
        stadium = createMatchRequestDto.stadium
    )

    fun toCreateMatchResponse(match: Match) = CreateMatchResponseDto(match.id!!)

    fun toMatchResponse(match: Match) = MatchResponseDto(
        id = match.id!!,
        leagueName = match.leagueName,
        awayClubName = match.awayClubName,
        homeClubName = match.homeClubName,
        date = match.date,
        stadium = match.stadium, // SCORE
        matchRealTimeEvents = match.realTimeMessages.map { MatchRealTimeEventsDto(time = it.time, text = it.text, team = it.team, type = it.type) }
    )
}
