package com.bootcamp.assignment1.exception

data class ErrorResponse(
    val statusCode: Int,
    val message: String?
)