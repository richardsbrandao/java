package com.richard.dynkot.system

import com.richard.dynkot.controllers.requests.CreateUserRequest
import com.richard.dynkot.controllers.responses.UserResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateUserControllerSystemTest(@Autowired val restTemplate: TestRestTemplate) {
    private val nameExample = "richardsbrandao@gmail.com"
    private val emailExample = "richard"
    private val endpoint = "/users"

    @Nested
    inner class SuccessfulCases {
        @Test
        fun should_create_user() {
            val request = CreateUserRequest(nameExample, emailExample)
            val response  = restTemplate.postForEntity(endpoint, request, UserResponse::class.java)

            assertEquals(HttpStatus.OK, response.statusCode)

            assertEquals(emailExample, response.body?.email)
            assertEquals(nameExample, response.body?.name)
        }
    }

    @Nested
    inner class FailingCases {
        @Test
        fun should_return_conflict_exception_when_try_to_create_existing_user() {

        }
    }
}
