package com.yuriserka.multitenant.core.datasources

import com.yuriserka.multitenant.core.datasources.domain.DatabaseProviders
import com.yuriserka.multitenant.core.datasources.usecases.ListDataSourceConfigs
import jakarta.annotation.PostConstruct
import mu.KotlinLogging
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.stereotype.Component
import javax.sql.DataSource

private val logger = KotlinLogging.logger {}

@Component
class DataSourceProperties(private val listDataSourceConfigs: ListDataSourceConfigs) {
    final val sources = mutableMapOf<String, DataSource>()

    @PostConstruct
    fun fetchDataSources(): Map<String, DataSource> {
        sources.putAll(getDataSourceProperties())
        logger.info { "Loaded data sources properties for tenants: ${sources.keys}" }
        return sources
    }

    private final fun getDataSourceProperties(): Map<String, DataSource> {
        return listDataSourceConfigs.execute().fold(mutableMapOf()) { tenantsDataSources, it ->
            tenantsDataSources[it.id.toString()] = DataSourceBuilder
                .create()
                .url("jdbc:${it.dbProvider.value}://${it.dbHost}:${it.dbPort}/${it.dbName}")
                .username(it.dbUsername)
                .password(it.dbPassword)
                .apply {
                    when (it.dbProvider) {
                        DatabaseProviders.POSTGRESQL -> driverClassName("org.postgresql.Driver")
                        DatabaseProviders.MYSQL -> driverClassName("com.mysql.cj.jdbc.Driver")
                    }
                }
                .build()
            tenantsDataSources
        }
    }
}