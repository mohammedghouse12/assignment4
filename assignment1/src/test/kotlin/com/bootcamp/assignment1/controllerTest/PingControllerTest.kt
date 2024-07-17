package com.bootcamp.assignment1.controllerTest

import com.bootcamp.assignment1.controller.PingController
import com.bootcamp.assignment1.service.implementations.PingService
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import io.mockk.every
import io.mockk.verify

@ExtendWith(MockKExtension::class)
class PingControllerTest {

    @MockK
    lateinit var pingService: PingService

    private lateinit var pingController: PingController

    @BeforeEach
    fun setUp(){
        pingController = PingController(pingService)
    }

    @Test
    fun testPingCalling(){
        every{pingService.pongAndCurrentTime()} returns "Ok"

        val result = pingController.returnTimeAndResponse()

        verify (exactly = 1){ pingService.pongAndCurrentTime()  }
    }


}