package com.richard.studies.realtime.controllers

import com.richard.studies.realtime.controllers.mappers.LeagueMapper
import com.richard.studies.realtime.controllers.mappers.LeagueResponse
import com.richard.studies.realtime.controllers.requests.CreateLeagueRequest
import com.richard.studies.realtime.controllers.responses.CreateLeagueResponse
import com.richard.studies.realtime.services.LeagueService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/leagues")
class LeagueController(
    private val leagueMapper: LeagueMapper,
    private val leagueService: LeagueService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@Valid @RequestBody request: CreateLeagueRequest): Mono<CreateLeagueResponse> {
        return Mono.just(request)
                .map(leagueMapper::toLeague)
                .flatMap(leagueService::save)
                .map(leagueMapper::toCreateLeagueResponse)
    }

    @GetMapping("/{leagueId}")
    fun findById(@PathVariable("leagueId") leagueId: String): Mono<LeagueResponse> {
        return leagueService.findById(leagueId)
                .map(leagueMapper::toLeagueResponse)
    }
}
