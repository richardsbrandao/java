package com.richard.dynkot.unit.services

import com.richard.dynkot.entities.User
import com.richard.dynkot.errors.NotFoundException
import com.richard.dynkot.repositories.UsersRepository
import com.richard.dynkot.services.UsersService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.net.ConnectException
import java.util.*

@ExtendWith(MockKExtension::class)
class UsersServiceTest {

    @MockK lateinit var usersRepository: UsersRepository
    @InjectMockKs lateinit var usersService: UsersService

    val userExample : User = User("email", "name")

    @Nested
    inner class CreateUserTests {
        @Test
        fun should_create_user_when_no_error_occurs() {
            every { usersRepository.save(userExample) } returns userExample

            val savedUser = usersService.save(userExample)

            assertEquals(userExample, savedUser)

            verify { usersRepository.save(userExample) }
        }

        @Test
        fun should_throw_error_when_it_occurs() {
            every { usersRepository.save(userExample) } throws ConnectException()

            assertThrows(ConnectException::class.java) { usersService.save(userExample) }

            verify { usersRepository.save(userExample) }
        }
    }

    @Nested
    inner class FindUserByEmailTests {
        @Test
        fun should_return_user_when_it_exists() {
            every { usersRepository.findById(userExample.email) } returns Optional.of(userExample)

            val user = usersService.findByEmail(userExample.email)

            assertEquals(userExample.email, user.email)
            assertEquals(userExample.name, user.name)

            verify { usersRepository.findById(userExample.email) }
        }

        @Test
        fun should_throw_not_found_error_when_it_does_not_exists() {
            every { usersRepository.findById(userExample.email) } returns Optional.empty()

            val notFoundException = assertThrows(NotFoundException::class.java) { usersService.findByEmail(userExample.email) }

            assertEquals("Entity with id ${userExample.email} not found", notFoundException.message)

            verify { usersRepository.findById(userExample.email) }
        }

        @Test
        fun should_throw_connection_error_when_it_happens() {
            every { usersRepository.findById(userExample.email) } throws ConnectException()

            assertThrows(ConnectException::class.java) { usersService.findByEmail(userExample.email) }

            verify { usersRepository.findById(userExample.email) }
        }
    }
}