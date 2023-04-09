package com.yuriserka.multitenant.core.todo.usecases

import com.yuriserka.multitenant.core.todo.domain.TodoDto


interface ListTodos {
    fun all(): List<TodoDto>

    fun byId(id: String): TodoDto
}