package com.richard.dynkot.controllers.requests

import com.richard.dynkot.entities.User

class CreateUserRequest(val name: String?, val email : String?) {
    fun toModel() : User {
        return User(email = email!!, name = name!!)
    }
}
