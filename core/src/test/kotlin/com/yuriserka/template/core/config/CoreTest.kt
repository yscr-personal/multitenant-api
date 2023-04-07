package com.yuriserka.template.core.config

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
annotation class CoreTest
