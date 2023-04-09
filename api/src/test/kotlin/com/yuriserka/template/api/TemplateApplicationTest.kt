package com.yuriserka.template.api

import com.yuriserka.template.api.config.ApiTest
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Test

@ApiTest
class TemplateApplicationTest {
    @InjectMockKs
    private lateinit var templateApplication: TemplateApplication

    @Test
    fun `should start application`() {
        assert(true)
    }
}