package com.richard.studies.realtime.repositories

import com.richard.studies.realtime.models.Match
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MatchRepository : ReactiveMongoRepository<Match, String>
