package com.richard.studies.realtime.repositories

import com.richard.studies.realtime.models.League
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface LeagueRepository : ReactiveMongoRepository<League, String>
