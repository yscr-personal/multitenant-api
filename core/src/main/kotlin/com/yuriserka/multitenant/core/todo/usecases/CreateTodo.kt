package com.yuriserka.multitenant.core.todo.usecases

import com.yuriserka.multitenant.core.todo.domain.TodoDto


interface CreateTodo {
    fun execute(todo: TodoDto): TodoDto
}