package com.yuriserka.template.core.config

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class ObjectMapperConfiguration {
    @Bean
    fun objectMapper(): Jackson2ObjectMapperBuilder {
        return Jackson2ObjectMapperBuilder()
            .indentOutput(true)
            .modulesToInstall(JavaTimeModule())
            .failOnUnknownProperties(false)
    }
}