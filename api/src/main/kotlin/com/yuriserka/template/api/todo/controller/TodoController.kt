package com.yuriserka.template.api.todo.controller

import com.yuriserka.template.api.todo.domain.TodoResponseDto
import com.yuriserka.template.core.todo.domain.TodoDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "todos", description = "The todos API")
interface TodoController {
    @Operation(summary = "Finds all todos", description = "Finds all todos from database and jsonplaceholder api")
    @ApiResponse(
        description = "Success",
        responseCode = "200",
        content = [Content(mediaType = "application/json")]
    )
    fun getTodos(): ResponseEntity<List<TodoResponseDto>>

    @Operation(summary = "Get todo by id")
    fun getTodoById(@PathVariable id: String): ResponseEntity<TodoResponseDto>

    @Operation(summary = "Create todo")
    fun createTodo(@RequestBody todo: TodoDto): ResponseEntity<TodoResponseDto>

    @Operation(summary = "Delete todo by id")
    fun deleteTodoById(@PathVariable id: String): ResponseEntity<Unit>

    @Operation(summary = "Update todo by id")
    fun updateTodoById(@PathVariable id: String, @RequestBody todo: TodoDto): ResponseEntity<TodoResponseDto>
}
