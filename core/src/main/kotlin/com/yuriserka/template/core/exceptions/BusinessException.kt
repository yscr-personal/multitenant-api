package com.yuriserka.template.core.exceptions

class BusinessException {
    enum class ErrorType {
        TODO_NOT_FOUND
    }

    open class Error(private val type: ErrorType, override val message: String) : RuntimeException(message)
}