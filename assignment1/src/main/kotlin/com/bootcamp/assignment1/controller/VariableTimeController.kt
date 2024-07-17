package com.bootcamp.assignment1.controller

import com.bootcamp.assignment1.service.implementations.TimeService
import com.bootcamp.assignment1.service.implementations.VariableTimeService
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VariableTimeController (val variableTimeService: VariableTimeService,
                              val environment: Environment, val timeService: TimeService
){

    @GetMapping("/time")
    fun personalizedTime() : ResponseEntity<String>{
        val result : String = variableTimeService.vairableTime(environment, timeService)
        return ResponseEntity(result, HttpStatus.OK)
    }
}