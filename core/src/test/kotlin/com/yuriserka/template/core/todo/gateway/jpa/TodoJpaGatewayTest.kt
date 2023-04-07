package com.yuriserka.template.core.todo.gateway.jpa

import com.yuriserka.template.core.config.CoreTest
import com.yuriserka.template.core.todo.domain.Todo
import com.yuriserka.template.core.todo.domain.TodoDto
import com.yuriserka.template.core.todo.domain.mapper.TodoMapper
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.justRun
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*

@CoreTest
class TodoJpaGatewayTest {
    @MockK
    private lateinit var todoRepository: TodoRepository

    @MockK
    private lateinit var todoMapper: TodoMapper

    @InjectMockKs
    private lateinit var todoJpaGateway: TodoJpaGateway

    @Test
    fun `should create todo`() {
        val dto = TodoDto(title = "title", completed = false)
        val expected = Todo(
            id = UUID.randomUUID(),
            title = dto.title,
            completed = dto.completed ?: false
        )

        every { todoMapper.toEntity(dto) } returns expected

        every { todoRepository.save(any()) } returns expected

        val result = todoJpaGateway.create(dto)

        assert(result == expected)
        verify(exactly = 1) { todoRepository.save(any()) }
    }

    @Test
    fun `should delete todo`() {
        val id = UUID.randomUUID().toString()

        justRun { todoRepository.deleteById(UUID.fromString(id)) }

        todoJpaGateway.deleteById(id)

        verify(exactly = 1) { todoRepository.deleteById(UUID.fromString(id)) }
    }

    @Test
    fun `should get todo by id`() {
        val id = UUID.randomUUID().toString()
        val expected = Todo(
            id = UUID.fromString(id),
            title = "title",
            completed = false
        )

        every { todoRepository.findById(UUID.fromString(id)) } returns Optional.of(expected)

        val result = todoJpaGateway.getById(id)

        assert(result == expected)
        verify(exactly = 1) { todoRepository.findById(UUID.fromString(id)) }
    }

    @Test
    fun `should get todos`() {
        val expected = listOf(
            Todo(
                id = UUID.randomUUID(),
                title = "title",
                completed = false
            )
        )

        every { todoRepository.findAll() } returns expected

        val result = todoJpaGateway.getAll()

        assert(result.isNotEmpty())
        assert(result == expected)
        verify(exactly = 1) { todoRepository.findAll() }
    }

    @Test
    fun `should update todo`() {
        val id = UUID.randomUUID().toString()

        val dto = TodoDto(title = "title", completed = false)

        every { todoRepository.findById(UUID.fromString(id)) } returns Optional.of(
            Todo(
                id = UUID.fromString(id),
                title = "title",
                completed = false
            )
        )

        every { todoRepository.save(any()) } returns Todo(
            id = UUID.fromString(id),
            title = dto.title,
            completed = dto.completed ?: false
        )

        val result = todoJpaGateway.update(id, dto)

        assert(result.id != null)
        assert(result.id.toString() == id)
        assert(result.title == dto.title)
        assert(result.completed == dto.completed)
        verify(exactly = 1) { todoRepository.save(any()) }
    }
}