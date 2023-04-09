package com.yuriserka.multitenant.core.todo.gateway.jpa

import com.yuriserka.multitenant.core.todo.domain.Todo
import com.yuriserka.multitenant.core.todo.domain.TodoDto
import com.yuriserka.multitenant.core.todo.domain.mapper.TodoMapper
import com.yuriserka.multitenant.core.todo.exceptions.TodoNotFound
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TodoJpaGateway(private val todoRepository: TodoRepository, private val todoMapper: TodoMapper) {
    fun create(todo: TodoDto): Todo {
        return todoRepository.save(
            Todo(
                title = todo.title,
                completed = todo.completed
            )
        )
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
        todo.completed = body.completed
        return todoRepository.save(todo)
    }
}