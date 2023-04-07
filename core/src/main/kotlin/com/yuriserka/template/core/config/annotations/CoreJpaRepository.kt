package com.yuriserka.template.core.config.annotations

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@ComponentScan(basePackages = ["com.yuriserka.template.core"])
@EnableJpaRepositories(basePackages = ["com.yuriserka.template.core"])
@EntityScan(basePackages = ["com.yuriserka.template.core"])
@EnableTransactionManagement
annotation class CoreJpaRepository
