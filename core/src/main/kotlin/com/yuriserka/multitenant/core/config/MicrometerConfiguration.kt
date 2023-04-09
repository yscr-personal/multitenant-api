package com.yuriserka.multitenant.core.config

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MicrometerConfiguration {
  @Bean
  fun timedAspect(registry: MeterRegistry): TimedAspect {
    return TimedAspect(registry)
  }
}
