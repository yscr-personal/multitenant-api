package com.yuriserka.multitenant.core.datasources

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component

@Component
class SchemaResolver : CurrentTenantIdentifierResolver {
    override fun resolveCurrentTenantIdentifier(): String {
        return TenantContextHolder.getTenantId() ?: "public"
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }
}