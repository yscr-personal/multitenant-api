package com.yuriserka.template.api.exceptions

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class ExceptionInterceptor {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun intercept(exception: Exception): ExceptionResponseDto {
        logger.error("${exception.javaClass.name}: ${exception.message}")
        return ExceptionResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.message ?: "Internal server error",
                500
        )
    }
}
