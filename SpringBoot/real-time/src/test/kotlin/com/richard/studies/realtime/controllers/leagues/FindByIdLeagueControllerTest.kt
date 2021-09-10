package com.richard.studies.realtime.controllers.leagues

import com.richard.studies.realtime.repositories.LeagueRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FindByIdLeagueControllerTest constructor(
        @Autowired private val webTestClient: WebTestClient,
        @Autowired private val leagueRepository: LeagueRepository
) {

    @Test
    fun `Should save league`() {
//        webTestClient.post()
//                .uri("/leagues")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(request))
//                .exchange()
//                .expectStatus().isCreated
//                .expectBody()
//                .jsonPath("$.id").isNotEmpty
    }

    // error tests ....
}
