package com.yuriserka.multitenant.core.config

import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource
import org.hibernate.cfg.Environment;

@Configuration
class HibernateConfiguration(
    private val jpaProperties: JpaProperties
) {
    @Bean
    fun jpaVendorAdapter(): JpaVendorAdapter {
        return HibernateJpaVendorAdapter()
    }

    @Bean
    fun entityManagerFactory(
        dataSource: DataSource,
        multiTenantConnectionProviderImpl: MultiTenantConnectionProvider,
        currentTenantIdentifierResolverImpl: CurrentTenantIdentifierResolver
    ): LocalContainerEntityManagerFactoryBean? {
        val jpaPropertiesMap: MutableMap<String, Any?> = mutableMapOf()
        jpaPropertiesMap.putAll(jpaProperties.properties)
        jpaPropertiesMap[Environment.MULTI_TENANT_CONNECTION_PROVIDER] = multiTenantConnectionProviderImpl
        jpaPropertiesMap[Environment.MULTI_TENANT_IDENTIFIER_RESOLVER] = currentTenantIdentifierResolverImpl
        val manager = LocalContainerEntityManagerFactoryBean()
        manager.dataSource = dataSource
        manager.jpaVendorAdapter = jpaVendorAdapter()
        manager.setJpaPropertyMap(jpaPropertiesMap)
        manager.setPackagesToScan("com.yuriserka.multitenant.core")
        return manager
    }
}