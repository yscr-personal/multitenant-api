package com.yuriserka.multitenant.api.todo.controller

import com.ninjasquad.springmockk.MockkBean
import com.yuriserka.multitenant.api.auth.JwtService
import com.yuriserka.multitenant.api.config.ApiTest
import com.yuriserka.multitenant.core.todo.domain.TodoDto
import com.yuriserka.multitenant.core.todo.usecases.CreateTodo
import com.yuriserka.multitenant.core.todo.usecases.DeleteTodo
import com.yuriserka.multitenant.core.todo.usecases.ListTodos
import com.yuriserka.multitenant.core.todo.usecases.UpdateTodo
import io.mockk.every
import io.mockk.justRun
import io.mockk.verify
import jakarta.annotation.PostConstruct
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@ApiTest
class TodoControllerTest {


    @MockkBean
    private lateinit var createTodo: CreateTodo

    @MockkBean
    private lateinit var deleteTodo: DeleteTodo

    @MockkBean
    private lateinit var listTodos: ListTodos

    @MockkBean
    private lateinit var updateTodo: UpdateTodo

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val path = "/todos"

    private val createBody = """
        {
            "title": "title",
            "completed": false
        }
    """.trimIndent()

    private val updateBody = """
        {
            "title": "title",
            "completed": true
        }
    """.trimIndent()

    @Autowired
    private lateinit var jwtService: JwtService
    private lateinit var jwtToken: String

    @PostConstruct
    fun setupTenant() {
        jwtToken = jwtService.generateToken("1")
    }

    @Test
    fun `should return all todos`() {
        every { listTodos.all() } returns listOf(
            TodoDto(
                id = "1",
                title = "title",
                completed = false
            )
        )

        mockMvc.perform(
            get(path)
                .header("authorization", "Bearer $jwtToken")
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].id").value("1"))
            .andExpect(jsonPath("\$.[0].title").value("title"))
            .andExpect(jsonPath("\$.[0].done").value(false))

        verify(exactly = 1) { listTodos.all() }
    }

    @Test
    fun `should return todo by id`() {
        val id = "1"
        every { listTodos.byId(id) } returns TodoDto(
            id = id,
            title = "title",
            completed = false
        )

        mockMvc.perform(
            get("$path/$id")
                .header("authorization", "Bearer $jwtToken")
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.id").value(id))
            .andExpect(jsonPath("\$.title").value("title"))
            .andExpect(jsonPath("\$.done").value(false))

        verify(exactly = 1) { listTodos.byId(id) }
    }

    @Test
    fun `should create todo`() {
        every { createTodo.execute(any()) } returns TodoDto(
            id = "1",
            title = "title",
            completed = false
        )

        mockMvc.perform(
            post(path)
                .header("authorization", "Bearer $jwtToken")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createBody)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.id").value("1"))
            .andExpect(jsonPath("\$.title").value("title"))
            .andExpect(jsonPath("\$.done").value(false))

        verify(exactly = 1) { createTodo.execute(any()) }
    }

    @Test
    fun `should delete todo by id`() {
        val id = "1"

        justRun { deleteTodo.execute(id) }

        mockMvc.perform(
            delete("$path/$id")
                .header("authorization", "Bearer $jwtToken")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)

        verify(exactly = 1) { deleteTodo.execute(id) }
    }

    @Test
    fun `should update todo by id`() {
        val id = UUID.randomUUID().toString()

        every { updateTodo.execute(id, any()) } returns TodoDto(
            id = id,
            title = "new-title",
            completed = true
        )

        mockMvc.perform(
            put("$path/$id")
                .header("authorization", "Bearer $jwtToken")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateBody)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.id").value(id))
            .andExpect(jsonPath("\$.title").value("new-title"))
            .andExpect(jsonPath("\$.done").value(true))

        verify(exactly = 1) { updateTodo.execute(id, any()) }
    }
}