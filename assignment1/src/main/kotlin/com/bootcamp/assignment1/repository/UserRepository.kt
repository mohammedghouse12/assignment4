package com.bootcamp.assignment1.repository

import com.bootcamp.assignment1.entity.User
import com.bootcamp.assignment1.entity.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): User?

    fun findByNationality(nationality: String): List<User>

    fun findUsersBySalaryGreaterThan(salary: Int): List<User>
}