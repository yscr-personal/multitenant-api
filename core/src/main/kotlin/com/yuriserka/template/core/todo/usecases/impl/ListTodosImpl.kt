package com.yuriserka.template.core.todo.usecases.impl

import com.yuriserka.template.core.todo.domain.TodoDto
import com.yuriserka.template.core.todo.domain.mapper.TodoMapper
import com.yuriserka.template.core.todo.gateway.http.TodoHttpGateway
import com.yuriserka.template.core.todo.gateway.jpa.TodoJpaGateway
import com.yuriserka.template.core.todo.usecases.ListTodos
import org.springframework.stereotype.Service

@Service
class ListTodosImpl(
    private val todoHttpGateway: TodoHttpGateway,
    private val todoJpaGateway: TodoJpaGateway,
    private val todoMapper: TodoMapper
) : ListTodos {
    override fun all(): List<TodoDto> {
        return todoJpaGateway.getAll().map { todoMapper.toDto(it) }.plus(todoHttpGateway.getTodos())
    }

    override fun byId(id: String): TodoDto {
        return todoMapper.toDto(todoJpaGateway.getById(id))
    }

}