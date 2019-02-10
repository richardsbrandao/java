package com.richard.dynkot.unit.controllers.requests

import com.richard.dynkot.controllers.requests.CreateUserRequest
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test

class CreateUserRequestTest {
    @Test
    fun should_convert_to_user() {
        val createUserRequest = CreateUserRequest("name", "email")
        val user = createUserRequest.toModel()

        assertEquals(createUserRequest.name, user.name)
        assertEquals(createUserRequest.email, user.email)
    }
}