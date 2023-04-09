package com.yuriserka.multitenant.core.todo.usecases

import com.yuriserka.multitenant.core.config.CoreTest
import com.yuriserka.multitenant.core.todo.domain.Todo
import com.yuriserka.multitenant.core.todo.domain.TodoDto
import com.yuriserka.multitenant.core.todo.domain.mapper.TodoMapper
import com.yuriserka.multitenant.core.todo.gateway.jpa.TodoJpaGateway
import com.yuriserka.multitenant.core.todo.usecases.impl.CreateTodoImpl
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*

@CoreTest
class CreateTodoTest {
    @MockK
    private lateinit var todoJpaGateway: TodoJpaGateway

    @MockK
    private lateinit var todoMapper: TodoMapper

    @InjectMockKs
    private lateinit var createTodo: CreateTodoImpl

    @Test
    fun `should create todo`() {
        val input = TodoDto(title = "title", completed = false)

        every { todoJpaGateway.create(input) } returns Todo(
            id = UUID.randomUUID(),
            title = input.title,
            completed = input.completed
        )

        every { todoMapper.toDto(any()) } returns input.copy(id = UUID.randomUUID().toString())

        val result = createTodo.execute(input)

        assert(result.title == input.title)
        assert(result.completed == input.completed)

        verify(exactly = 1) { todoJpaGateway.create(input) }
        verify(exactly = 1) { todoMapper.toDto(any()) }
    }
}