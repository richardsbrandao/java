package com.richard.studies.realtime.controllers.requests

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class CreateLeagueRequest(
        @JsonProperty("country") @field:NotBlank val country: String? = null,
        @JsonProperty("division") @field:NotBlank val division: String? = null,
        @JsonProperty("season") @field:NotBlank val season: String? = null
)
