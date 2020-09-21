package com.richard.studies.realtime.controllers.leagues

import com.richard.studies.realtime.controllers.requests.CreateLeagueRequest
import com.richard.studies.realtime.repositories.LeagueRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

private val request = CreateLeagueRequest(
    country = "BR",
    division = "A",
    season = "2020"
)

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SaveLeagueControllerTest constructor(
    @Autowired private val webTestClient: WebTestClient,
    @Autowired private val leagueRepository: LeagueRepository
) {
    @BeforeEach
    fun beforeEach() {
        leagueRepository.deleteAll().subscribe()
    }

    @Test
    fun `Should save league`() {
        webTestClient.post()
                .uri("/leagues")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isCreated
                .expectBody()
                .jsonPath("$.id").isNotEmpty
    }

    // error tests ....
}
