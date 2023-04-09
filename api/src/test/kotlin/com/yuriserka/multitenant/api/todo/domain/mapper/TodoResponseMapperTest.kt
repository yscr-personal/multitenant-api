package com.yuriserka.multitenant.api.todo.domain.mapper

import com.yuriserka.multitenant.core.todo.domain.TodoDto
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.util.*

class TodoResponseMapperTest {
    private val todoResponseMapper = Mappers.getMapper(TodoResponseMapper::class.java)

    @Test
    fun `should map to response`() {
        val uuid = UUID.randomUUID()
        val todoDto = TodoDto(
            id = uuid.toString(),
            title = "title",
            completed = true
        )

        val todo = todoResponseMapper.toResponse(todoDto)

        assert(todo.id == uuid.toString())
        assert(todo.title == "title")
        assert(todo.done)
    }
}