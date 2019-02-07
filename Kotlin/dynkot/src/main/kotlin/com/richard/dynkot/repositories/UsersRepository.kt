package com.richard.dynkot.repositories

import com.richard.dynkot.entities.User
import org.springframework.stereotype.Repository

@Repository
class UsersRepository {
    fun findByEmail(email : String) : User = User(email, "Hello")
}