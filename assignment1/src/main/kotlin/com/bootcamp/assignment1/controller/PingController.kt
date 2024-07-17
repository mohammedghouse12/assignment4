package com.bootcamp.assignment1.controller

import com.bootcamp.assignment1.service.implementations.PingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController(val pingService: PingService) {

    @GetMapping("/ping")
    fun returnTimeAndResponse() : ResponseEntity<String>{
        val result : String = pingService.pongAndCurrentTime()
        return ResponseEntity(result, HttpStatus.OK)
    }

}