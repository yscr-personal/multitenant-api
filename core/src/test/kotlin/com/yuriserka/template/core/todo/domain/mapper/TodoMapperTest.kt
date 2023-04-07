package com.yuriserka.template.core.todo.domain.mapper

import com.yuriserka.template.core.todo.domain.Todo
import com.yuriserka.template.core.todo.domain.TodoDto
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.util.UUID

class TodoMapperTest {
    private val todoMapper = Mappers.getMapper(TodoMapper::class.java)

    @Test
    fun `should map to entity`() {
        val uuid = UUID.randomUUID()
        val todoDto = TodoDto(
            id = uuid.toString(),
            title = "title",
        )

        val todo = todoMapper.toEntity(todoDto)

        assert(todo.id == uuid)
        assert(todo.title == "title")
        assert(!todo.completed)
    }

    @Test
    fun `should map to dto`() {
        val uuid = UUID.randomUUID()
        val todo = Todo(
            id = uuid,
            title = "title",
            completed = false,
        )

        val todoDto = todoMapper.toDto(todo)

        assert(todoDto.id == uuid.toString())
        assert(todoDto.title == "title")
        assert(todoDto.completed == false)
    }
}