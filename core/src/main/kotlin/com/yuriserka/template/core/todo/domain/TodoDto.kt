package com.yuriserka.template.core.todo.domain

import com.yuriserka.template.core.config.annotations.NoArg
import java.util.UUID

@NoArg
data class TodoDto(
    val id: String? = UUID.randomUUID().toString(),
    val title: String,
    val completed: Boolean? = false
)
