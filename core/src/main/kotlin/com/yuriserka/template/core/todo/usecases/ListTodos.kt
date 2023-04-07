package com.yuriserka.template.core.todo.usecases

import com.yuriserka.template.core.todo.domain.TodoDto

interface ListTodos {
    fun all(): List<TodoDto>

    fun byId(id: String): TodoDto
}