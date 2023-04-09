package com.yuriserka.multitenant.core.datasources

class TenantContextHolder {
    companion object {
        private val contextHolder = ThreadLocal<String>()

        fun setTenantId(tenantId: String) = contextHolder.set(tenantId)

        fun getTenantId(): String? = contextHolder.get()

        fun clear() = contextHolder.remove()
    }
}