package com.richard.dynkot.services

import com.richard.dynkot.entities.User
import com.richard.dynkot.errors.NotFoundException
import com.richard.dynkot.repositories.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(private val usersRepository : UsersRepository) {

    fun findByEmail(email : String) : User {
        val user = usersRepository.findById(email)
        if( user.isPresent ) {
            throw NotFoundException(email)
        }
        return user.get()
    }

    fun save(user: User) : User {
        return usersRepository.save(user)
    }
}
