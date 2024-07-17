package com.bootcamp.assignment1.controller

import com.bootcamp.assignment1.entity.User
import com.bootcamp.assignment1.service.UserService
import com.bootcamp.assignment1.utils.DTOUtils
import com.bootcamp.assignment1.utils.DateUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserWithDetailsController(val userService: UserService) {

    @PostMapping("/v1/users")
    fun addUser(@RequestBody user: User): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(DTOUtils.toUserDTO(userService.addUser(user)))
    }

    @PostMapping("/v2/users")
    fun addUserV2(@RequestBody user: User): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(DTOUtils.toUserDTOV2(userService.addUser(user)))
    }

    @GetMapping("/users/{id}")
    fun findUserById(@PathVariable id: Int): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id))
    }

    @GetMapping("/users/email/{email}")
    fun findUserByEmail(@PathVariable email: String): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(email))
    }

    @GetMapping("/users")
    fun getUsers(@RequestParam(required = false) nationality: String?): ResponseEntity<Any> {
        if (nationality.isNullOrBlank())
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll())

        return ResponseEntity.status(HttpStatus.OK).body(userService.findUsersByNationality(nationality))

    }

    @DeleteMapping("/users/{id}")
    fun deleteUserById(@PathVariable id: Int): ResponseEntity<Any> {
        userService.deleteUser(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("")
    }

    @PutMapping("/v1/users/{id}")
    fun updateUserById(@PathVariable id: Int, @RequestBody user: User): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(DTOUtils.toUserDTO(userService.updateUser(id, user)))
    }

    @PutMapping("/v2/users/{id}")
    fun updateUserByIdV2(@PathVariable id: Int, @RequestBody user: User): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(DTOUtils.toUserDTOV2(userService.updateUser(id, user)))
    }

    @GetMapping("/users/salaryGreaterThan")
    fun getUsersBySalaryGreaterThan(@RequestParam salary: Int): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersWithSalaryGreaterThan(salary))
    }
}





