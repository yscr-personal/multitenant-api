package com.yuriserka.multitenant.api.exceptions

import org.springframework.http.HttpStatus

data class ExceptionResponseDto(
    var httpStatus: HttpStatus,
    var message: String,
    var status: Long = httpStatus.value().toLong()
)
