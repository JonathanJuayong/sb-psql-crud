package com.example.sbpsqlcrud.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException (override val message: String): Exception() {
    private val serialVersionUID: Long = 1L
}