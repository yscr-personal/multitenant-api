package com.yuriserka.multitenant.core.todo.gateway.jpa

import com.yuriserka.multitenant.core.todo.domain.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TodoRepository : JpaRepository<Todo, UUID>