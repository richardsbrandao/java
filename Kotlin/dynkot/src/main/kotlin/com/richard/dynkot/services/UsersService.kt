package com.richard.dynkot.services

import com.richard.dynkot.entities.User
import com.richard.dynkot.repositories.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(private val usersRepository : UsersRepository) {

    fun findByEmail(email : String) : User {
        val user = User(email + Math.random(), "R")
        usersRepository.save(user)
        return user
    }
}
