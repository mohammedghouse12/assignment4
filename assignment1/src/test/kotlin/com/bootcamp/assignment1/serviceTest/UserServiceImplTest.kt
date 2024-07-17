package com.bootcamp.assignment1.serviceTest

import com.bootcamp.assignment1.entity.User
import com.bootcamp.assignment1.entity.UserDetails
import com.bootcamp.assignment1.exception.ResourceNotFoundException
import com.bootcamp.assignment1.repository.UserRepository
import com.bootcamp.assignment1.service.UserService
import com.bootcamp.assignment1.service.implementations.UserServiceImpl
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class UserServiceImplTest {

    @MockK
    private lateinit var userRepository: UserRepository

    private lateinit var userService: UserService

    @BeforeEach
    fun setUp(){
        userService = UserServiceImpl(userRepository)
    }

    @Test
    fun testFindAll(){
        val users = listOf(
            User(name = "Alice", email = "alice@navi.com", nationality = "USA", salary = 20),
            User(name = "Bob", email = "bob@navi.com", nationality = "Unknown", salary = 30),
            User(name = "Charlie", email = "charlie@navi.com", nationality = "UK", salary = 40)
        )

        every{ userRepository.findAll()} returns users

        val result = userService.findAll()
        assertEquals(result, users)
    }

    @Test
    fun testFindUserByIdIfUserExists(){
        val user : User = User( "Alice",  "alice@navi.com", "USA", 20)

        val userId = 1

        every{ userRepository.findById(userId)} returns Optional.of(user)

        val result = userService.findUserById(userId)

        assertEquals(result, user)
    }

    @Test
    fun testFindUserByIdIfUserDoesNotExists(){
        val user : User = User( "Alice",  "alice@navi.com", "USA", 20)

        val userId = 4

        every{ userRepository.findById(userId)} returns Optional.empty()

        val exception = assertThrows(ResourceNotFoundException::class.java) {
            val result = userService.findUserById(userId)
        }

        assertEquals("User with id $userId not found", exception.message)
    }

    @Test
    fun testFindUserByEmailIfUserExists(){
        val user : User = User( "Alice",  "alice@navi.com", "USA", 20)

        val email = "alice@navi.com"

        every{ userRepository.findByEmail(email)} returns user

        val result = userService.findUserByEmail(email)
        assertEquals(result, user)
    }

    @Test
    fun testFindUserByEmailIfUserDoesNotExists(){
        val user : User = User( "Alice",  "alice@navi.com", "USA", 20)

        val email = "chandler@navi.com"

        every{ userRepository.findByEmail(email)} throws ResourceNotFoundException("User with email $email not found")

        val exception = assertThrows(ResourceNotFoundException::class.java){
            val result = userService.findUserByEmail(email)
        }

        assertEquals(exception.message, ("User with email $email not found"))
    }

    @Test
    fun testFindUsersByNationality(){
        val users = listOf(
            User(name = "Alice", email = "alice@navi.com", nationality = "USA", salary = 20),
            User(name = "Bob", email = "bob@navi.com", nationality = "USA", salary = 30),
            User(name = "Charlie", email = "charlie@navi.com", nationality = "USA", salary = 40)
        )

        val nationality = "USA"

        every{ userRepository.findByNationality(nationality)} returns users

        val result = userService.findUsersByNationality(nationality)

        assertEquals(result, users)
    }

    @Test
    fun testFailsToFindUsersByNationality(){
        val nationality = "India"
        every{ userRepository.findByNationality(nationality)} returns emptyList()

        val result = userService.findUsersByNationality(nationality)

        assertEquals(result, emptyList())
    }

    @Test
    fun testDelete(){
        val user : User = User( "Alice",  "alice@navi.com", "USA", 20)
        val id : Int = 1

        every { userRepository.delete(user) } returns Unit
        every { userRepository.findById(id) } returns Optional.of(user)

        userService.deleteUser(id)
        verify(exactly = 1) { userRepository.delete(user) }
    }

    @Test
    fun testAdd(){
        val user : User = User( "Alice",  "alice@navi.com", "USA", 20)

        every{userRepository.save(user)} returns user

        val result : User = userService.addUser(user)

        assertEquals(user, result)
        verify(exactly=1) { userRepository.save(user) }
    }

    @Test
    fun testUpdate(){
        val user : User = User( "Alice",  "alice@navi.com", "USA", 20)
        user.id = 1

        val existingUser : User = User( "Alice",  "alice@navi.com", "USA", 20)
        existingUser.id = 1

        val id : Int = existingUser.id
        val email : String = user.email

        every { userRepository.save(user) } returns existingUser
        every { userRepository.findById(id) } returns Optional.of(existingUser)

        val result : User = userService.updateUser(user)

        assertEquals(user.id, result.id)
    }


}