package com.yuriserka.template.core.todo.domain

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
    @Id @Column(name = "id", unique = true, nullable = false)
    val id: UUID? = UUID.randomUUID(),
    var title: String,
    var completed: Boolean,
    @Version
    val version: Long? = null,
    var createdAt: LocalDateTime? = LocalDateTime.now(),
    var updatedAt: LocalDateTime? = LocalDateTime.now()
) {
    override fun equals(other: Any?) = when {
        this === other -> true
        javaClass != other?.javaClass -> false
        id != (other as Todo).id -> false
        else -> true
    }

    override fun hashCode(): Int = id.hashCode()
}
