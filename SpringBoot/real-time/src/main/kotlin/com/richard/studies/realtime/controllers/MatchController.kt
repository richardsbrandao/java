package com.richard.studies.realtime.controllers

import com.richard.studies.realtime.controllers.requests.CreateMatchRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("leagues/{leagueId}/matches")
class MatchController {
    @PostMapping
    fun save(
        @PathVariable("leagueId") leagueId: String,
        @Valid @RequestBody createMatchRequest: CreateMatchRequest
    ) {

    }
}
