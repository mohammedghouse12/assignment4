package com.bootcamp.assignment1.controllerTest

import com.bootcamp.assignment1.controller.UserWithDetailsController
import com.bootcamp.assignment1.entity.User
import com.bootcamp.assignment1.entity.dto.UserDTO
import com.bootcamp.assignment1.entity.UserDetails
import com.bootcamp.assignment1.exception.ResourceNotFoundException
import com.bootcamp.assignment1.service.UserService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class UserUserDetailControllerTest {

    @MockK
    private lateinit var userService: UserService

    @InjectMocks
    private lateinit var userDetailsController: UserWithDetailsController

    @BeforeEach
    fun setUp(){
        userDetailsController = UserWithDetailsController(userService)
    }

    @Test
    fun testAddUserDetails(){
        val userDetails = UserDetails().apply {
            age = 22
            dateOfBirth = "2000-05-25"
            gender = "Female"
        }
        val user : User = User( "Alice",  "alice@navi.com", "USA", 20)
        val userDto = UserDTO(user, userDetails)

        every { userService.addUser(user) } returns user
        every { userDetailsService.addUserDetails(userDetails) } returns userDetails
        every { userService.findUserByEmail(user.email) } throws ResourceNotFoundException("")

        val response = userDetailsController.addUser(userDto)
        assertEquals(response.statusCode, HttpStatus.CREATED)
        assertEquals(response.body, userDto)
    }

    @Test
    fun testFindUserDetailsById(){
        val userDetails = UserDetails().apply {
            age = 22
            dateOfBirth = "2000-05-25"
            gender = "Female"
        }
        val user : User = User( "Alice",  "alice@navi.com", "USA", 20)
        val userDto = UserDTO(user, userDetails)

        val id : Int = 1
        user.id = id
        userDetails.id = id

        every { userDetailsService.findUserDetailsById(id) } returns userDetails
        every { userService.findUserById(id) } returns user

        val response = userDetailsController.findUserById(id)
        assertEquals(response.statusCode, HttpStatus.OK)
        assertEquals(response.body, userDto)
    }

    @Test
    fun testDeleteUserDetailsById(){
        val id = 1
        every { userService.deleteUser(id) } returns Unit
        val response = userDetailsController.deleteUserById(id)
        assertEquals(response.statusCode, HttpStatus.NO_CONTENT)
    }

    @Test
    fun testFailsToDeleteUserDetailsById(){
        val id = 1
        every { userService.deleteUser(id) } throws ResourceNotFoundException("User with id $id not found")

        val exception = assertThrows(ResourceNotFoundException::class.java){
            val response = userDetailsController.deleteUserById(id)
        }
        assertEquals("User with id $id not found", exception.message)
    }

    @Test
    fun testUpdateUserDetailsById(){
        val userDetails = UserDetails().apply {
            age = 22
            dateOfBirth = "2000-05-25"
            gender = "Female"
        }
        val user : User = User( "Alice",  "alice@navi.com", "USA", 20)
        val userDto = UserDTO(user, userDetails)

        val id : Int = 1

        every { userDetailsService.updateUserDetails(userDetails) } returns userDetails
        every { userService.updateUser(user) } returns user

        val response = userDetailsController.updateUserById(id, userDto)
        assertEquals(response.statusCode, HttpStatus.OK)
    }

}
