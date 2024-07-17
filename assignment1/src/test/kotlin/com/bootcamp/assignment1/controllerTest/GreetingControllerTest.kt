package com.bootcamp.assignment1.controllerTest

import com.bootcamp.assignment1.controller.GreetingController
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals


@ExtendWith(MockKExtension::class)
class GreetingControllerTest {

    private lateinit var greetingController: GreetingController

    @BeforeEach
    fun setUP(){
        greetingController = GreetingController()
    }

    @Test
    fun testGreetings(){
        val name = "Ghouse"

        val result = greetingController.greeting(name);
        val expected = "Hello, "+name

        assertEquals(result.body, expected)
    }
}