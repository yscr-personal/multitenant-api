package com.yuriserka.multitenant.api.todo.domain.mapper

import com.yuriserka.multitenant.api.todo.domain.TodoResponseDto
import com.yuriserka.multitenant.core.todo.domain.TodoDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface TodoResponseMapper {
    @Mapping(target = "done", source = "completed")
    fun toResponse(todo: TodoDto): TodoResponseDto
}
