package com.yuriserka.multitenant.api.todo.domain

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "TodoResponseDto", description = "Todo response dto")
data class TodoResponseDto(
    var id: String,
    @field:Schema(example = "Buy milk", description = "Title of todo", required = true)
    var title: String,
    var done: Boolean
)
