package com.richard.studies.realtime.controllers.requests

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class CreateClubRequest(
    @JsonProperty("name") @field:NotBlank val name: String? = null,
    @JsonProperty("country") @field:NotBlank  val country: String? = null,
    @JsonProperty("image") @field:NotBlank  val image: String? = null
)
