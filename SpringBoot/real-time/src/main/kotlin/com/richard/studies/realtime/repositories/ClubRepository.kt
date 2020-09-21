package com.richard.studies.realtime.repositories

import com.richard.studies.realtime.models.Club
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface ClubRepository : ReactiveCrudRepository<Club, UUID>
