package com.yuriserka.template.core.todo.usecases.impl

import com.yuriserka.template.core.todo.domain.TodoDto
import com.yuriserka.template.core.todo.domain.mapper.TodoMapper
import com.yuriserka.template.core.todo.gateway.jpa.TodoJpaGateway
import com.yuriserka.template.core.todo.usecases.UpdateTodo
import org.springframework.stereotype.Service

@Service
class UpdateTodoImpl(private val todoJpaGateway: TodoJpaGateway, private val todoMapper: TodoMapper) : UpdateTodo {
    override fun execute(id: String, body: TodoDto): TodoDto {
        return todoMapper.toDto(todoJpaGateway.update(id, body))
    }
}