package com.richard.studies.realtime.controllers

import com.richard.studies.realtime.controllers.mapper.MatchMapper
import com.richard.studies.realtime.controllers.request.CreateMatchRequestDto
import com.richard.studies.realtime.controllers.response.CreateMatchResponseDto
import com.richard.studies.realtime.controllers.response.MatchResponseDto
import com.richard.studies.realtime.models.Score
import com.richard.studies.realtime.services.MatchService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("matches")
class MatchController(
    private val matchService: MatchService,
    private val matchMapper: MatchMapper
) {

    @PostMapping
    fun create(@RequestBody matchRequestDto: CreateMatchRequestDto): Mono<CreateMatchResponseDto> {
        return Mono.just(matchMapper.toMatch(matchRequestDto))
                .flatMap(matchService::save)
                .map(matchMapper::toCreateMatchResponse)
    }

    @GetMapping("/{matchId}")
    fun findById(@PathVariable("matchId") matchId: String): Mono<MatchResponseDto> {
        return matchService.findById(matchId)
            .map(matchMapper::toMatchResponse)
    }

    @GetMapping
    fun findAll(): Flux<MatchResponseDto> {
        return matchService.findAll()
            .map(matchMapper::toMatchResponse)
    }
}
