package com.yuriserka.multitenant.core.todo.domain.mapper

import com.yuriserka.multitenant.core.todo.domain.Todo
import com.yuriserka.multitenant.core.todo.domain.TodoDto
import org.mapstruct.Mapper

@Mapper
interface TodoMapper {
    fun toDto(todo: Todo): TodoDto

    fun toEntity(todoDto: TodoDto): Todo
}