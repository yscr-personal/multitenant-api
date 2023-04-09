package com.yuriserka.multitenant.core.datasources.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "data_source_configurations")
class DataSourceConfig(
    @Id @Column(name = "id", unique = true, nullable = false) var id: UUID = UUID.randomUUID(),
    @Column(name = "db_password") var dbPassword: String,
    @Column(name = "db_username") var dbUsername: String,
    @Column(name = "db_port") var dbPort: String,
    @Column(name = "db_name") var dbName: String,
    @Column(name = "db_host") var dbHost: String,
    @Column(name = "db_provider") @Enumerated(EnumType.STRING) var dbProvider: DatabaseProviders,
    @Version var version: Long = 0L,
    @Column(name = "created_at") var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at") var updatedAt: LocalDateTime = LocalDateTime.now(),
)
