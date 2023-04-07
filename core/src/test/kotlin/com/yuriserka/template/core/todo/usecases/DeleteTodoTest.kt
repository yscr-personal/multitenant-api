package com.yuriserka.template.core.todo.usecases

import com.yuriserka.template.core.config.CoreTest
import com.yuriserka.template.core.todo.gateway.jpa.TodoJpaGateway
import com.yuriserka.template.core.todo.usecases.impl.DeleteTodoImpl
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.justRun
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*

@CoreTest
class DeleteTodoTest {
    @MockK
    private lateinit var todoJpaGateway: TodoJpaGateway

    @InjectMockKs
    private lateinit var deleteTodo: DeleteTodoImpl

    @Test
    fun `should delete todo`() {
        val id = UUID.randomUUID().toString()

        justRun { todoJpaGateway.deleteById(id) }

        deleteTodo.execute(id)

        verify(exactly = 1) { todoJpaGateway.deleteById(id) }
    }
}