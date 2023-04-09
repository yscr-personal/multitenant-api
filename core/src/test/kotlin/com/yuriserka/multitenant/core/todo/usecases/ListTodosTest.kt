package com.yuriserka.multitenant.core.todo.usecases

import com.yuriserka.multitenant.core.config.CoreTest
import com.yuriserka.multitenant.core.todo.domain.Todo
import com.yuriserka.multitenant.core.todo.domain.TodoDto
import com.yuriserka.multitenant.core.todo.domain.mapper.TodoMapper
import com.yuriserka.multitenant.core.todo.exceptions.TodoNotFound
import com.yuriserka.multitenant.core.todo.gateway.http.TodoHttpGateway
import com.yuriserka.multitenant.core.todo.gateway.jpa.TodoJpaGateway
import com.yuriserka.multitenant.core.todo.usecases.impl.ListTodosImpl
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*
import org.junit.jupiter.api.assertThrows

@CoreTest
class ListTodosTest {
    @MockK
    private lateinit var todoHttpGateway: TodoHttpGateway

    @MockK
    private lateinit var todoJpaGateway: TodoJpaGateway

    @MockK
    private lateinit var todoMapper: TodoMapper

    @InjectMockKs
    private lateinit var listTodos: ListTodosImpl

    @Test
    fun `should list all todos`() {
        every { todoHttpGateway.getTodos() } returns listOf(
            TodoDto(id = "1", title = "title", completed = false)
        )
        every { todoJpaGateway.getAll() } returns listOf(
            Todo(id = UUID.randomUUID(), title = "title-db", completed = false)
        )
        every { todoMapper.toDto(any()) } returns TodoDto(id = "3", title = "title", completed = false)

        val result = listTodos.all()

        assert(result.size == 2)
        verify(exactly = 1) { todoHttpGateway.getTodos() }
        verify(exactly = 1) { todoJpaGateway.getAll() }
        verify(exactly = 1) { todoMapper.toDto(any()) }
    }

    @Test
    fun `should list todo by id`() {
        val id = UUID.randomUUID().toString()

        every { todoJpaGateway.getById(id) } returns Todo(
            id = UUID.fromString(id),
            title = "title-db",
            completed = false
        )

        every { todoMapper.toDto(any()) } returns TodoDto(id = id, title = "title", completed = false)

        val result = listTodos.byId(id)

        assert(result.id == id)
        verify(exactly = 1) { todoJpaGateway.getById(id) }
        verify(exactly = 1) { todoMapper.toDto(any()) }
    }

    @Test
    fun `should throw Exception if todo not found`() {
        val id = UUID.randomUUID().toString()

        every { todoJpaGateway.getById(id) } throws TodoNotFound(id)

        assertThrows<TodoNotFound> { listTodos.byId(id) }
    }
}