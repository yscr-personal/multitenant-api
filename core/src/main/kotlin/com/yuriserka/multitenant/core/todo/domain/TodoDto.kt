package com.yuriserka.multitenant.core.todo.domain

import com.yuriserka.multitenant.core.config.annotations.NoArg
import java.util.*

@NoArg
data class TodoDto(
    var id: String = UUID.randomUUID().toString(),
    var title: String,
    var completed: Boolean = false
)
