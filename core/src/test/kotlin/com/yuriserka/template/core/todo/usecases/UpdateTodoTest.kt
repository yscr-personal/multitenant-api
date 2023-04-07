package com.yuriserka.template.core.todo.usecases

import com.yuriserka.template.core.config.CoreTest
import com.yuriserka.template.core.todo.domain.Todo
import com.yuriserka.template.core.todo.domain.TodoDto
import com.yuriserka.template.core.todo.domain.mapper.TodoMapper
import com.yuriserka.template.core.todo.gateway.jpa.TodoJpaGateway
import com.yuriserka.template.core.todo.usecases.impl.UpdateTodoImpl
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.UUID

@CoreTest
class UpdateTodoTest {
    @MockK
    private lateinit var todoJpaGateway: TodoJpaGateway

    @MockK
    private lateinit var todoMapper: TodoMapper

    @InjectMockKs
    private lateinit var updateTodo: UpdateTodoImpl

    @Test
    fun `should update todo`() {
        val id = UUID.randomUUID().toString()
        val input = TodoDto(title = "title", completed = false)

        every { todoJpaGateway.update(id, input) } returns Todo(
            id = UUID.fromString(id),
            title = input.title,
            completed = input.completed ?: false
        )

        every { todoMapper.toDto(any()) } returns input.copy(id = id)

        val result = updateTodo.execute(id, input)

        assert(result.id != null)
        assert(result.id == id)
        assert(result.title == input.title)
        assert(result.completed == input.completed)

        verify(exactly = 1) { todoJpaGateway.update(id, input) }
        verify(exactly = 1) { todoMapper.toDto(any()) }
    }
}