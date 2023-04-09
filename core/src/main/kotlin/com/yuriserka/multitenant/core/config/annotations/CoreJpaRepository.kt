package com.yuriserka.multitenant.core.config.annotations

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableJpaRepositories(basePackages = ["com.yuriserka.multitenant.core"])
@EntityScan(basePackages = ["com.yuriserka.multitenant.core"])
@EnableTransactionManagement
annotation class CoreJpaRepository
