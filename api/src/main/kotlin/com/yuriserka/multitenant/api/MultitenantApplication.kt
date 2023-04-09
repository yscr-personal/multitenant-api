package com.yuriserka.multitenant.api

import com.yuriserka.multitenant.core.config.annotations.CoreJpaRepository
import com.yuriserka.multitenant.core.config.annotations.CorePropertySource
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = ["com.yuriserka.multitenant"])
@ComponentScan(basePackages = ["com.yuriserka.multitenant"])
@EnableFeignClients(basePackages = ["com.yuriserka.multitenant"])
@ImportAutoConfiguration(FeignAutoConfiguration::class)
@CorePropertySource
@CoreJpaRepository
@EnableCaching
class MultitenantApplication

fun main(args: Array<String>) {
    runApplication<MultitenantApplication>(*args)
}
