package com.richard.studies.realtime.repositories

import com.richard.studies.realtime.models.League
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface LeagueRepository : ReactiveCrudRepository<League, Long>
