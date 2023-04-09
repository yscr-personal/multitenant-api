package com.yuriserka.multitenant.api.config

import com.ninjasquad.springmockk.MockkBean
import com.yuriserka.multitenant.api.MultitenantApplication
import com.yuriserka.multitenant.core.datasources.usecases.ListDataSourceConfigs
import io.mockk.every
import jakarta.annotation.PostConstruct
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(classes = [MultitenantApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
annotation class ApiTest {
    @TestConfiguration
    class TestConfig {
        @MockkBean
        private lateinit var listDataSourceConfigs: ListDataSourceConfigs

        @PostConstruct
        fun initMock() {
            every { listDataSourceConfigs.execute() } returns listOf()
        }
    }
}
