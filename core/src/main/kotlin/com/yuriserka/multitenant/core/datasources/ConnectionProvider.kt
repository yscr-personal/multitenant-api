package com.yuriserka.multitenant.core.datasources

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class ConnectionProvider(
    private val defaultDataSource: DataSource,
    private val applicationContext: ApplicationContext
) : AbstractDataSourceBasedMultiTenantConnectionProviderImpl() {

    override fun selectAnyDataSource(): DataSource {
        return defaultDataSource
    }

    override fun selectDataSource(tenantId: String): DataSource {
        val bean = applicationContext.getBean(DataSourceProperties::class.java)
        return bean.sources[tenantId] ?: defaultDataSource
    }
}
