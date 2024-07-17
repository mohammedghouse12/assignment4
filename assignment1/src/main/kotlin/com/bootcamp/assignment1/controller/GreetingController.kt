package com.bootcamp.assignment1.controller

import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(
        @NotNull @RequestParam(value = "name") name : String
    ) : ResponseEntity<String>{
        val result : String = "Hello, $name"
        return ResponseEntity(result, HttpStatus.OK)
    }
}