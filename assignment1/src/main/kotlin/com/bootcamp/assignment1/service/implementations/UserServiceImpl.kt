package com.bootcamp.assignment1.service.implementations

import com.bootcamp.assignment1.entity.User
import com.bootcamp.assignment1.exception.EmailAlreadyExistsException
import com.bootcamp.assignment1.exception.ResourceNotFoundException
import com.bootcamp.assignment1.repository.UserRepository
import com.bootcamp.assignment1.service.UserService
import com.bootcamp.assignment1.utils.DateUtils
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class UserServiceImpl(val userRepository: UserRepository) : UserService {

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    override fun addUser(user: User): User {
        val userAlreadyExists = userRepository.findByEmail(user.email)
        if (userAlreadyExists != null) throw EmailAlreadyExistsException("User with email ${user.email} already exists!")
        user.id = 0
        user.userDetails?.user = user
        return userRepository.save(user)
    }

    override fun findUserById(id: Int): User {
        return userRepository.findById(id).orElseThrow { ResourceNotFoundException("User with id $id not found") }
    }

    override fun findUserByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw ResourceNotFoundException("User with email $email not found")
    }

    override fun findUsersByNationality(nationality: String): List<User> {
        return userRepository.findByNationality(nationality)
    }

    override fun deleteUser(id: Int): Unit {
        val user: User = findUserById(id)
        userRepository.delete(user)
    }

    override fun updateUser(id: Int, user: User): User {
        val existingUser = findUserById(id)

        if (existingUser.email != user.email && userRepository.findByEmail(user.email) != null) {
            throw EmailAlreadyExistsException("User with email ${user.email} already exists")
        }

        existingUser.email = user.email
        existingUser.name = user.name
        existingUser.nationality = user.nationality
        existingUser.salary = user.salary

        user.userDetails?.let { newUserDetails ->
            if (existingUser.userDetails != null) {
                existingUser.userDetails?.age = newUserDetails.age
                existingUser.userDetails?.dateOfBirth = newUserDetails.dateOfBirth
                existingUser.userDetails?.gender = newUserDetails.gender
            } else {
                existingUser.addUserDetails(newUserDetails)
            }
        }

        return userRepository.save(existingUser)
    }


    override fun getUsersWithSalaryGreaterThan(salary: Int): List<User> {
        return userRepository.findUsersBySalaryGreaterThan(salary)
    }
}





