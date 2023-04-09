package com.yuriserka.multitenant.api.todo.controller.impl

import com.yuriserka.multitenant.api.todo.controller.TodoController
import com.yuriserka.multitenant.api.todo.domain.TodoResponseDto
import com.yuriserka.multitenant.api.todo.domain.mapper.TodoResponseMapper
import com.yuriserka.multitenant.core.todo.domain.TodoDto
import com.yuriserka.multitenant.core.todo.usecases.CreateTodo
import com.yuriserka.multitenant.core.todo.usecases.DeleteTodo
import com.yuriserka.multitenant.core.todo.usecases.ListTodos
import com.yuriserka.multitenant.core.todo.usecases.UpdateTodo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoControllerImpl(
    private val todoResponseMapper: TodoResponseMapper,
    private val listTodos: ListTodos,
    private val createTodo: CreateTodo,
    private val deleteTodo: DeleteTodo,
    private val updateTodo: UpdateTodo
) : TodoController {
    @GetMapping
    override fun getTodos(): ResponseEntity<List<TodoResponseDto>> {
        return ResponseEntity.ok(listTodos.all().map { todoResponseMapper.toResponse(it) })
    }

    @GetMapping("/{id}")
    override fun getTodoById(@PathVariable id: String): ResponseEntity<TodoResponseDto> {
        return ResponseEntity.ok(todoResponseMapper.toResponse(listTodos.byId(id)))
    }

    @PostMapping
    override fun createTodo(@RequestBody todo: TodoDto): ResponseEntity<TodoResponseDto> {
        return ResponseEntity.ok(todoResponseMapper.toResponse(createTodo.execute(todo)))
    }

    @DeleteMapping("/{id}")
    override fun deleteTodoById(@PathVariable id: String): ResponseEntity<Unit> {
        deleteTodo.execute(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    override fun updateTodoById(@PathVariable id: String, @RequestBody todo: TodoDto): ResponseEntity<TodoResponseDto> {
        return ResponseEntity.ok(todoResponseMapper.toResponse(updateTodo.execute(id, todo)))
    }
}