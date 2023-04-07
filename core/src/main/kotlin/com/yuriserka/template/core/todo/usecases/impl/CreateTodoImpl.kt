package com.yuriserka.template.core.todo.usecases.impl

import com.yuriserka.template.core.todo.domain.TodoDto
import com.yuriserka.template.core.todo.domain.mapper.TodoMapper
import com.yuriserka.template.core.todo.gateway.jpa.TodoJpaGateway
import com.yuriserka.template.core.todo.usecases.CreateTodo
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class CreateTodoImpl(private val todoJpaGateway: TodoJpaGateway, private val todoMapper: TodoMapper) : CreateTodo {
    override fun execute(todo: TodoDto): TodoDto {
        val createdTodo = todoJpaGateway.create(todo)
        val todoDto = todoMapper.toDto(createdTodo)
        logger.info { "created todo: $todoDto" }
        return todoDto
    }
}