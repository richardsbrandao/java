package com.richard.studies.realtime.services

import com.richard.studies.realtime.models.Club
import com.richard.studies.realtime.models.League
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

private const val LEAGUE_ID = "1"
private val club = Club(name = "AC Milan", country = "IT", image = "http://..")
private val league = League(id = "a", country = "IT", division = "A", season = "2020/2021")

@ExtendWith(MockKExtension::class)
class ClubServiceTest {
    @InjectMockKs
    private lateinit var clubService: ClubService

    @MockK
    private lateinit var leagueService: LeagueService

    @Test
    fun `Should create club`() {
        val leagueSlot = slot<League>()
        every { leagueService.findById(any()) } returns Mono.just(league)
        every { leagueService.save(capture(leagueSlot)) } returns Mono.just(league)

        StepVerifier.create(clubService.save(club = club, leagueId = LEAGUE_ID))
                .expectNext(club)
                .verifyComplete()

        val savedLeague = leagueSlot.captured

        verify { leagueService.findById(LEAGUE_ID) }
        verify { leagueService.save(savedLeague) }

        assertEquals(league.id, savedLeague.id)
        assertEquals(league.id, savedLeague.country)
        assertEquals(league.id, savedLeague.division)
        assertEquals(league.id, savedLeague.season)

        assertEquals(1, savedLeague.clubs.size)
        assertTrue(savedLeague.clubs.contains(club))
    }

    @Test
    fun `Should throw error when can't save`() {
        every { leagueService.findById(any()) } returns Mono.just(league)
        every { leagueService.save(any()) } returns Mono.error(RuntimeException())

        StepVerifier.create(clubService.save(club = club, leagueId = LEAGUE_ID))
                .verifyError(RuntimeException::class.java)

        verify { leagueService.findById(LEAGUE_ID) }
        verify { leagueService.save(league) }
    }

    @Test
    fun `Should throw error when league doesn't exist`() {
        every { leagueService.findById(any()) } returns Mono.error(RuntimeException())

        StepVerifier.create(clubService.save(club = club, leagueId = LEAGUE_ID))
                .verifyError(RuntimeException::class.java)

        verify { leagueService.findById(LEAGUE_ID) }
        verify(exactly = 0) { leagueService.save(any()) }
    }

}
