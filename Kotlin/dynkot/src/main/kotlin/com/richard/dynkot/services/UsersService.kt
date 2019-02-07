package com.richard.dynkot.services

import com.richard.dynkot.entities.User
import com.richard.dynkot.repositories.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(private val usersRepository : UsersRepository) {

    fun findByEmail(email : String) : User = usersRepository.findByEmail(email)
}
