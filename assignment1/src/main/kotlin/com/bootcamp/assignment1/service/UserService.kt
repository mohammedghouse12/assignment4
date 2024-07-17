package com.bootcamp.assignment1.service

import com.bootcamp.assignment1.entity.User
import java.util.*

interface UserService {
    fun findAll(): List<User>

    fun addUser(user: User): User

    fun findUserById(id: Int): User

    fun findUserByEmail(email: String): User

    fun findUsersByNationality(nationality: String): List<User>

    fun deleteUser(id: Int): Unit

    fun updateUser(id: Int, user: User): User

    fun getUsersWithSalaryGreaterThan(salary: Int): List<User>
}





