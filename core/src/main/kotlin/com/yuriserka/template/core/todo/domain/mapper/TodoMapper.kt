package com.yuriserka.template.core.todo.domain.mapper

import com.yuriserka.template.core.todo.domain.Todo
import com.yuriserka.template.core.todo.domain.TodoDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
interface TodoMapper {
    fun toDto(todo: Todo): TodoDto

    fun toEntity(todoDto: TodoDto): Todo
}