package com.example.sbpsqlcrud.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.*

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundException(
        exception: ResourceNotFoundException,
        request: WebRequest) : ResponseEntity<Any> {
        val errorDetails = ErrorDetails(Date(), exception.message, request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(
        exception: Exception,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorDetails = ErrorDetails(Date(), exception.message ?: "", request.getDescription(false))
        return ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR)
    }
}