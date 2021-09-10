package com.richard.studies.realtime.services

import com.richard.studies.realtime.models.Club
import com.richard.studies.realtime.models.League
import com.richard.studies.realtime.models.Match
import com.richard.studies.realtime.models.Score
import com.richard.studies.realtime.repositories.MatchRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.OffsetDateTime

private const val LEAGUE_ID = "1"

private val match = Match(
    leagueId = LEAGUE_ID,
    homeClubId = "A1",
    awayClubId = "A2",
    score = Score(home = 1, away = 0),
    stadium = "San Siro",
    date = OffsetDateTime.now()
)

private val league = League(
    id = LEAGUE_ID,
    country = "IT",
    division = "A",
    season = "2020/2021",
    clubs = mutableSetOf(
        Club(id = "A1", name = "AC Milan", country = "IT", image = ""),
        Club(id = "A2", name = "Juventus", country = "IT", image = ""),
        Club(id = "A3", name = "Inter de Milao", country = "IT", image = "")
    )
)

@ExtendWith(MockKExtension::class)
class MatchServiceTest {

    @MockK
    private lateinit var leagueService: LeagueService

    @MockK
    private lateinit var matchRepository: MatchRepository

    @InjectMockKs
    private lateinit var matchService: MatchService

    @Test
    fun `Should save Match when league exist`() {
        every { leagueService.findById(any()) } returns Mono.just(league)
        every { matchRepository.save<Match>(any()) } returns Mono.just(match)

        StepVerifier.create(matchService.save(match))
            .expectNext(match)
            .verifyComplete()

        verify {
            leagueService.findById(LEAGUE_ID)
            matchRepository.save(match)
        }
    }

    @Test
    fun `Should not save Match when league doesn't exist`() {
        every { leagueService.findById(any()) } returns Mono.empty()

        StepVerifier.create(matchService.save(match))
                .verifyErrorMessage("Invalid League")

        verify { leagueService.findById(LEAGUE_ID) }
        verify(exactly = 0) { matchRepository.save<Match>(any()) }
    }

    @Test
    fun `Should not save Match when homeClub doesn't exist`() {
        val invalidMatch = match.copy(homeClubId = "A5")
        every { leagueService.findById(any()) } returns Mono.just(league)

        StepVerifier.create(matchService.save(invalidMatch))
                .verifyErrorMessage("Invalid HomeClub")

        verify { leagueService.findById(LEAGUE_ID) }
        verify(exactly = 0) { matchRepository.save<Match>(any()) }
    }

    @Test
    fun `Should not save Match when awayClub doesn't exist`() {
        val invalidMatch = match.copy(awayClubId =  "A5")
        every { leagueService.findById(any()) } returns Mono.just(league)

        StepVerifier.create(matchService.save(invalidMatch))
                .verifyErrorMessage("Invalid AwayClub")

        verify { leagueService.findById(LEAGUE_ID) }
        verify(exactly = 0) { matchRepository.save<Match>(any()) }
    }
}
