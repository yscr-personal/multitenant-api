package com.yuriserka.multitenant.core.todo.gateway.http

import com.yuriserka.multitenant.core.config.CoreTest
import com.yuriserka.multitenant.core.todo.domain.TodoDto
import com.yuriserka.multitenant.core.todo.gateway.http.clients.JsonPlaceholderTodoApiClient
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test

@CoreTest
class TodoHttpGatewayTest {
    @MockK
    private lateinit var todoApiClient: JsonPlaceholderTodoApiClient

    @InjectMockKs
    private lateinit var todoHttpGateway: TodoHttpGateway

    @Test
    fun `should get todos`() {
        every { todoApiClient.getTodos() } returns listOf(
            TodoDto(id = "1", title = "title", completed = false)
        )

        val result = todoHttpGateway.getTodos()

        assert(result.isNotEmpty())
        verify(exactly = 1) { todoApiClient.getTodos() }
    }

    @Test
    fun `should return empty list on fallback`() {
        val exception = RuntimeException("Mock-Error")

        val result = todoHttpGateway.getTodosFallback(exception)

        assert(result.isEmpty())
    }
}