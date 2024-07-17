package com.bootcamp.assignment1.entity.dto

data class UserDTO(
    val name : String,
    val email : String,
    val nationality : String,
    val salary : Int,
    val userDetails : UserDetailsDTO?
)