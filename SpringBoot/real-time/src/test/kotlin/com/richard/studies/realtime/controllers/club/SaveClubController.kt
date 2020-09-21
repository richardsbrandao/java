package com.richard.studies.realtime.controllers.club

import com.richard.studies.realtime.controllers.requests.CreateClubRequest
import com.richard.studies.realtime.repositories.ClubRepository
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
    @Autowired private val clubRepository: ClubRepository
) {
    @BeforeEach
    fun beforeEach() {
        clubRepository.deleteAll().subscribe()
    }

    @Test
    fun `Should save club`() {
        webTestClient.post()
                .uri("/clubs")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isCreated
                .expectBody()
                .jsonPath("$.id").isNotEmpty
    }
}
