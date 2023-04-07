package com.yuriserka.template.api.todo.domain

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "TodoResponseDto", description = "Todo response dto")
data class TodoResponseDto(
    val id: String,
    @field:Schema(example = "Buy milk", description = "Title of todo", required = true)
    val title: String,
    val done: Boolean
)
