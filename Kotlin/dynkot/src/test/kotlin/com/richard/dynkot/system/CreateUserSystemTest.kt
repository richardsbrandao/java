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
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

//@Profile("test")
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateUserControllerSystemTest(@Autowired val restTemplate: TestRestTemplate) {
    private val nameExample = "richard"
    private val emailExample = "richardsbrandao@gmail.com"
    private val endpoint = "/users"

    @Nested
    inner class SuccessfulCases {
        @Test
        fun should_create_user() {
            val request = CreateUserRequest(name = nameExample, email = emailExample)
            val response  = restTemplate.postForEntity(endpoint, request, UserResponse::class.java)

            assertEquals(HttpStatus.OK, response.statusCode)

            assertEquals(emailExample, response.body?.email)
            assertEquals(nameExample, response.body?.name)
        }

        @Test
        fun should_return_success_when_create_with_email_that_already_exists() {

        }

        @Test
        fun should_return_success_and_update_name_when_create_with_email_that_already_exists_and_different_name() {

        }
    }

    @Nested
    inner class FailingCases {
        @Test
        fun should_return_unprocessable_entity_when_try_to_create_with_invalid_email() {

        }

        @Test
        fun should_return_bad_request_when_try_to_create_with_null_or_blank_parameters() {

        }
    }
}
