package com.yuriserka.template.api.todo.domain.mapper

import com.yuriserka.template.api.todo.domain.TodoResponseDto
import com.yuriserka.template.core.todo.domain.TodoDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface TodoResponseMapper {
    @Mapping(target = "done", source = "completed")
    fun toResponse(todo: TodoDto): TodoResponseDto
}
