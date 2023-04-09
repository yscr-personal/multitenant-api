package com.yuriserka.multitenant.core.datasources

import com.yuriserka.multitenant.core.datasources.domain.DatabaseProviders
import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Configuration

@Configuration
class DataSourceConfiguration(private val dataSourceProperties: DataSourceProperties) {
    @PostConstruct
    fun migrate() {
        dataSourceProperties.sources.values.forEach { dataSource ->
            val vendor = DatabaseProviders.valueOf(dataSource.connection.metaData.databaseProductName.uppercase())
            Flyway
                .configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration/tenants/${vendor.value}")
                .validateOnMigrate(true)
                .baselineOnMigrate(true)
                .load()
                .migrate()
        }
    }
}