package com.yuriserka.multitenant.core.todo.usecases.impl

import com.yuriserka.multitenant.core.todo.domain.TodoDto
import com.yuriserka.multitenant.core.todo.domain.mapper.TodoMapper
import com.yuriserka.multitenant.core.todo.gateway.jpa.TodoJpaGateway
import com.yuriserka.multitenant.core.todo.usecases.CreateTodo
import org.springframework.stereotype.Service

@Service
class CreateTodoImpl(private val todoJpaGateway: TodoJpaGateway, private val todoMapper: TodoMapper) : CreateTodo {
    override fun execute(todo: TodoDto): TodoDto {
        val createdTodo = todoJpaGateway.create(todo)
        return todoMapper.toDto(createdTodo)
    }
}