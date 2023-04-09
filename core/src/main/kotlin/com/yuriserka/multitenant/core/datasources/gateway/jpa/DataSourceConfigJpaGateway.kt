package com.yuriserka.multitenant.core.datasources.gateway.jpa

import com.yuriserka.multitenant.core.datasources.domain.DataSourceConfig
import org.springframework.stereotype.Service

@Service
class DataSourceConfigJpaGateway(private val dataSourceConfigRepository: DataSourceConfigRepository) {
    fun list(): List<DataSourceConfig> {
        return dataSourceConfigRepository.findAll()
    }
}
