package com.bootcamp.assignment1.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.format.DateTimeParseException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(exec: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.NOT_FOUND.value(), exec.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exec: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.CONFLICT.value(), exec.message)
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse)
    }

    @ExceptionHandler(DateTimeParseException::class)
    fun handleDateTimeParseException(exception: DateTimeParseException) : ResponseEntity<ErrorResponse>{
        val errorResponse = ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(EmailAlreadyExistsException::class)
    fun handleDateTimeParseException(exception: EmailAlreadyExistsException) : ResponseEntity<ErrorResponse>{
        val errorResponse = ErrorResponse(HttpStatus.CONFLICT.value(), exception.message)
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse)
    }
}





