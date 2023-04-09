package com.yuriserka.multitenant.core.datasources.usecases

import com.yuriserka.multitenant.core.datasources.domain.DataSourceConfig

interface ListDataSourceConfigs {
    fun execute(): List<DataSourceConfig>
}