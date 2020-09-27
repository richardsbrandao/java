package com.richard.studies.realtime.controllers.mappers

import com.richard.studies.realtime.controllers.requests.CreateClubRequest
import com.richard.studies.realtime.controllers.responses.ClubResponse
import com.richard.studies.realtime.models.Club
import org.springframework.stereotype.Component

@Component
class ClubMapper {
    fun toClub(clubRequest: CreateClubRequest): Club {
        return Club(
            name = clubRequest.name!!,
            country = clubRequest.country!!,
            image = clubRequest.image!!
        )
    }

    fun toClubResponse(club: Club): ClubResponse {
        return ClubResponse(id = club.id!!)
    }
}
