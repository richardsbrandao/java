package com.richard.studies.realtime.controllers.club

import com.richard.studies.realtime.controllers.requests.CreateClubRequest
import com.richard.studies.realtime.models.League
import com.richard.studies.realtime.repositories.LeagueRepository
import org.awaitility.Awaitility.await
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
import java.util.concurrent.TimeUnit

private val league = League(id="5f70d93059bbdf29b4c78640", country = "IT", division = "A", season = "2020/2021")

private val request = CreateClubRequest(
        country = "IT",
        name = "AC Milan",
        image = "https://ssl.gstatic.com/onebox/media/sports/logos/VoKsJ6RitaHGhsM62e6AXQ_96x96.png"
)

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SaveClubController (
    @Autowired private val webTestClient: WebTestClient,
    @Autowired private val leagueRepository: LeagueRepository
) {
    @BeforeEach
    fun beforeEach() {
        leagueRepository.deleteAll()
                .then(leagueRepository.save(league))
                .subscribe()
    }

    @Test
    fun `Should save club`() {
        await().timeout(2000L, TimeUnit.MILLISECONDS).until {
            leagueRepository.findById(league.id!!).block() != null
        }

        webTestClient.post()
                .uri("leagues/${league.id}/clubs")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isCreated
                .expectBody()
                .jsonPath("$.id").isNotEmpty
    }
}
