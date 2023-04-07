package com.yuriserka.template.core.todo.gateway.http

import com.yuriserka.template.core.todo.domain.TodoDto
import com.yuriserka.template.core.todo.gateway.http.clients.JsonPlaceholderTodoApiClient
import io.github.resilience4j.retry.annotation.Retry
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class TodoHttpGateway(private val jsonPlaceholderTodoApiClient: JsonPlaceholderTodoApiClient) {
    @Retry(name = "jsonplaceholder-api-get-todos", fallbackMethod = "getTodosFallback")
    fun getTodos(): List<TodoDto> {
        logger.info { "Getting todos from jsonplaceholder" }
        return jsonPlaceholderTodoApiClient.getTodos()
    }

    fun getTodosFallback(exception: Exception): List<TodoDto> {
        logger.error(exception) { "Error getting todos from jsonplaceholder with message ${exception.message}" }
        return emptyList()
    }
}