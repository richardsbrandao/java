package com.richard.studies.realtime.controllers

import com.richard.studies.realtime.controllers.mappers.ClubMapper
import com.richard.studies.realtime.controllers.requests.CreateClubRequest
import com.richard.studies.realtime.controllers.responses.ClubResponse
import com.richard.studies.realtime.services.ClubService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/clubs")
class ClubController(
    private val clubMapper: ClubMapper,
    private val clubService: ClubService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@Valid @RequestBody request: CreateClubRequest): Mono<ClubResponse> {
        return Mono.just(request)
                .map(clubMapper::toClub)
                .flatMap(clubService::save)
                .map(clubMapper::toClubResponse)
    }
}
