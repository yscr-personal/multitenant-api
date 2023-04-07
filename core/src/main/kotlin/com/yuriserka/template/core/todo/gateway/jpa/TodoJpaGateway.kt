package com.yuriserka.template.core.todo.gateway.jpa

import com.yuriserka.template.core.todo.domain.Todo
import com.yuriserka.template.core.todo.domain.TodoDto
import com.yuriserka.template.core.todo.domain.mapper.TodoMapper
import com.yuriserka.template.core.todo.exceptions.TodoNotFound
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TodoJpaGateway(private val todoRepository: TodoRepository, private val todoMapper: TodoMapper) {
    fun create(todo: TodoDto): Todo {
        return todoRepository.save(todoMapper.toEntity(todo))
    }

    fun getAll(): List<Todo> {
        return todoRepository.findAll()
    }

    fun getById(id: String): Todo {
        return todoRepository
            .findById(UUID.fromString(id))
            .orElseThrow { TodoNotFound(id) }
    }

    fun deleteById(id: String) {
        todoRepository.deleteById(UUID.fromString(id))
    }

    fun update(id: String, body: TodoDto): Todo {
        val todo = getById(id)
        todo.title = body.title
        todo.completed = body.completed ?: todo.completed
        return todoRepository.save(todo)
    }
}