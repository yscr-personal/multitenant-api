package com.yuriserka.multitenant.core.datasources.gateway.jpa

import com.yuriserka.multitenant.core.datasources.domain.DataSourceConfig
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DataSourceConfigRepository : JpaRepository<DataSourceConfig, UUID>
