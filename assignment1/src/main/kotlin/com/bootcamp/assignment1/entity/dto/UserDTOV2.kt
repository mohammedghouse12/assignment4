package com.bootcamp.assignment1.entity.dto

data class UserDTOV2(
    val name : String,
    val email : String,
    val nationality : String,
    val salary : Int,
    val userDetails : UserDetailsDTOV2?
)