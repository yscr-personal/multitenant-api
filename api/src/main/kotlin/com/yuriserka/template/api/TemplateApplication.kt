package com.yuriserka.template.api

import com.yuriserka.template.core.config.annotations.CoreJpaRepository
import com.yuriserka.template.core.config.annotations.CorePropertySource
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = ["com.yuriserka.template"])
@ComponentScan(basePackages = ["com.yuriserka.template"])
@EnableFeignClients(basePackages = ["com.yuriserka.template"])
@ImportAutoConfiguration(FeignAutoConfiguration::class)
@CorePropertySource
@CoreJpaRepository
class TemplateApplication

fun main(args: Array<String>) {
    runApplication<TemplateApplication>(*args)
}
