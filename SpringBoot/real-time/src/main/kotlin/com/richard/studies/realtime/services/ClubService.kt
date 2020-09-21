package com.richard.studies.realtime.services

import com.richard.studies.realtime.models.Club
import com.richard.studies.realtime.repositories.ClubRepository
import org.springframework.stereotype.Service

@Service
class ClubService(private val clubRepository: ClubRepository) {
    fun save(club: Club) = clubRepository.save(club)
}
