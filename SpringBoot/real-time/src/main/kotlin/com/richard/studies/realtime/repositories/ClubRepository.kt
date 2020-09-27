package com.richard.studies.realtime.repositories

import com.richard.studies.realtime.models.Club
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import java.util.UUID

interface ClubRepository : ReactiveMongoRepository<Club, UUID>
