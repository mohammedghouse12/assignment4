package com.bootcamp.assignment1.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping("/hello")
    fun sayHelloWorld() : ResponseEntity<String>{
        val result : String = "Hello World"
        return ResponseEntity(result, HttpStatus.OK)
    }
}