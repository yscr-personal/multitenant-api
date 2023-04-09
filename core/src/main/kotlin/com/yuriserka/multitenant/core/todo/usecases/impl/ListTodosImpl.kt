package com.yuriserka.multitenant.core.todo.usecases.impl

import com.yuriserka.multitenant.core.todo.domain.TodoDto
import com.yuriserka.multitenant.core.todo.domain.mapper.TodoMapper
import com.yuriserka.multitenant.core.todo.gateway.http.TodoHttpGateway
import com.yuriserka.multitenant.core.todo.gateway.jpa.TodoJpaGateway
import com.yuriserka.multitenant.core.todo.usecases.ListTodos
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ListTodosImpl(
    private val todoHttpGateway: TodoHttpGateway,
    private val todoJpaGateway: TodoJpaGateway,
    private val todoMapper: TodoMapper
) : ListTodos {
    @Cacheable(value = ["tenants_todos"])
    override fun all(): List<TodoDto> {
        return todoJpaGateway.getAll().map { todoMapper.toDto(it) }.plus(todoHttpGateway.getTodos())
    }

    @Cacheable(value = ["tenants_todos"])
    override fun byId(id: String): TodoDto {
        return todoMapper.toDto(todoJpaGateway.getById(id))
    }
}