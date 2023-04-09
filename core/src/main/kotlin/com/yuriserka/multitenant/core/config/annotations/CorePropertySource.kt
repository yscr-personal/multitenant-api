package com.yuriserka.multitenant.core.config.annotations

import org.springframework.context.annotation.PropertySource

@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-\${spring.profiles.active}.properties")
annotation class CorePropertySource
