package com.yuriserka.multitenant.api.exceptions

import com.yuriserka.multitenant.core.exceptions.BusinessException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class ExceptionInterceptor {
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun intercept(exception: IllegalArgumentException) = buildResponse(exception, HttpStatus.BAD_REQUEST)


    @ExceptionHandler(Exception::class, BusinessException.Error::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun intercept(exception: Exception) = buildResponse(exception)

    private fun buildResponse(
        exception: Exception,
        status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    ): ExceptionResponseDto {
        logger.error("${exception.javaClass.name}: ${exception.message}")
        return ExceptionResponseDto(
            status,
            exception.message ?: "Internal server error",
        )
    }
}
