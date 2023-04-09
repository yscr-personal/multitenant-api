package com.yuriserka.multitenant.core.todo.usecases

import com.yuriserka.multitenant.core.todo.domain.TodoDto


interface UpdateTodo {
    fun execute(id: String, body: TodoDto): TodoDto
}