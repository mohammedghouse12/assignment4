package com.bootcamp.assignment1.utils

import com.bootcamp.assignment1.entity.*
import com.bootcamp.assignment1.entity.dto.UserDTO
import com.bootcamp.assignment1.entity.dto.UserDTOV2
import com.bootcamp.assignment1.entity.dto.UserDetailsDTO
import com.bootcamp.assignment1.entity.dto.UserDetailsDTOV2

object DTOUtils {

    fun toUserDTO(user : User) : UserDTO {
        return UserDTO(user.name, user.email, user.nationality, user.salary,
            UserDetailsDTO(user.userDetails?.age, user.userDetails?.dateOfBirth, user.userDetails?.gender)
        )
    }

    fun toUserDTOV2(user : User) : UserDTOV2 {
        return UserDTOV2(user.name, user.email, user.nationality, user.salary,
            UserDetailsDTOV2(user.userDetails?.dateOfBirth, user.userDetails?.gender)
        )
    }
}