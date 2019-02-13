package com.richard.dynkot.controllers

import com.richard.dynkot.controllers.requests.CreateUserRequest
import com.richard.dynkot.controllers.responses.UserResponse
import com.richard.dynkot.entities.User
import com.richard.dynkot.services.UsersService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UsersController(private val usersService: UsersService) {

    @PostMapping
    fun create(@RequestBody createUserRequest : CreateUserRequest) : UserResponse {
        val user : User = usersService.save(createUserRequest.toModel())
        return UserResponse(user.email, user.name)
    }

    @GetMapping("/{email}")
    fun findByEmail(@PathVariable("email") email : String) : UserResponse {
        val user = usersService.findByEmail(email)
        return UserResponse(user.email, user.name)
    }
}