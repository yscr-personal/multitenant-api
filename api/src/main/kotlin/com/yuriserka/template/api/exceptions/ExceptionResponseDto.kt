package com.yuriserka.template.api.exceptions

import org.springframework.http.HttpStatus

data class ExceptionResponseDto(val httpStatus: HttpStatus, val message: String, val status: Long)
