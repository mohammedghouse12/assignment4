package com.bootcamp.assignment1.service.implementations

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class TimeService {
    fun getCurrentTime() : String{
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        return currentTime.format(formatter)
    }
}