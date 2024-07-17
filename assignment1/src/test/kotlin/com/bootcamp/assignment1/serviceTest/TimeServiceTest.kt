package com.bootcamp.assignment1.serviceTest

import com.bootcamp.assignment1.service.implementations.TimeService
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@ExtendWith(MockKExtension::class)
class TimeServiceTest {

    private lateinit var timeService: TimeService

    @BeforeEach
    fun setUp(){
        timeService = TimeService()
    }

    @Test
    fun testGetCurrentTime(){
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

        val beforeCall = LocalDateTime.now()

        val result = timeService.getCurrentTime().substring(0, 16)

        val afterCall = LocalDateTime.now()

        val beforeCallTime = beforeCall.format(formatter).substring(0, 16)
        val afterCalltime = afterCall.format(formatter).substring(0, 16)

        assertTrue() {
            result >= beforeCallTime && result <= afterCalltime
        }
    }
}