package com.yuriserka.multitenant.core.datasources.usecases.impl

import com.yuriserka.multitenant.core.datasources.domain.DataSourceConfig
import com.yuriserka.multitenant.core.datasources.gateway.jpa.DataSourceConfigJpaGateway
import com.yuriserka.multitenant.core.datasources.usecases.ListDataSourceConfigs
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ListDataSourceConfigsImpl(
    private val dataSourceConfigJpaGateway: DataSourceConfigJpaGateway
) : ListDataSourceConfigs {
    @Cacheable(value = ["data_source_configs"])
    override fun execute(): List<DataSourceConfig> {
        return dataSourceConfigJpaGateway.list()
    }
}