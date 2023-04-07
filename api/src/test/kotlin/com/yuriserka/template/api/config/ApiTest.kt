package com.yuriserka.template.api.config

import com.yuriserka.template.api.TemplateApplication
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(classes = [TemplateApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
annotation class ApiTest
