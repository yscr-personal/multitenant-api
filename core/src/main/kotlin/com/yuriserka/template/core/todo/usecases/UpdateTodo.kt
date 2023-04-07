package com.yuriserka.template.core.todo.usecases

import com.yuriserka.template.core.todo.domain.TodoDto

interface UpdateTodo {
    fun execute(id: String, body: TodoDto): TodoDto
}