package com.yuriserka.multitenant.core.todo.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Version
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table
class Todo(
    @Id @Column(name = "id", unique = true, nullable = false) var id: UUID = UUID.randomUUID(),
    var title: String,
    var completed: Boolean,
    @Version val version: Long = 0L,
    @Column(name = "created_at") var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at") var updatedAt: LocalDateTime = LocalDateTime.now()
)
