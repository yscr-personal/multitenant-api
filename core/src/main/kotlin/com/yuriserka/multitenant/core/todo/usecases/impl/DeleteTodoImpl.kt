package com.yuriserka.multitenant.core.todo.usecases.impl

import com.yuriserka.multitenant.core.todo.gateway.jpa.TodoJpaGateway
import com.yuriserka.multitenant.core.todo.usecases.DeleteTodo
import org.springframework.stereotype.Service

@Service
class DeleteTodoImpl(private val todoJpaGateway: TodoJpaGateway) : DeleteTodo {
    override fun execute(id: String) {
        todoJpaGateway.deleteById(id)
    }
}