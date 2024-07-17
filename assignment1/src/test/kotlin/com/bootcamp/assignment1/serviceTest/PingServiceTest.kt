package com.bootcamp.assignment1.serviceTest

import com.bootcamp.assignment1.service.implementations.PingService
import com.bootcamp.assignment1.service.implementations.TimeService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class PingServiceTest {

    @MockK
    private lateinit var timeService: TimeService

    @InjectMocks
    private lateinit var pingService: PingService


    @BeforeEach
    fun setUp(){
        pingService = PingService(timeService)
    }

    @Test
    fun testPondAndCurrentTime(){
        val time = "11-07-2024 15:25:20"

        every { timeService.getCurrentTime() } returns time

        val result = pingService.pongAndCurrentTime()
        val expected = "Pong, "+time


        assertEquals(result, expected)
    }
}