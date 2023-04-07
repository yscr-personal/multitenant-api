package com.yuriserka.template.core.config

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean

class MicrometerConfiguration {
  @Bean
  fun timedAspect(registry: MeterRegistry): TimedAspect {
    return TimedAspect(registry)
  }
}
