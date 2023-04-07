package com.yuriserka.template.core.todo.usecases

import com.yuriserka.template.core.todo.domain.TodoDto

interface CreateTodo {
    fun execute(todo: TodoDto): TodoDto
}