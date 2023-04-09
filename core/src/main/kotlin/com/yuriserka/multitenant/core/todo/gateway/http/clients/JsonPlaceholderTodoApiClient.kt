package com.yuriserka.multitenant.core.todo.gateway.http.clients

import com.yuriserka.multitenant.core.todo.domain.TodoDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "jsonPlaceholderTodoApiClient", url = "\${jsonplaceholder.api.url}")
interface JsonPlaceholderTodoApiClient {
    @GetMapping("/todos")
    fun getTodos(): List<TodoDto>
}