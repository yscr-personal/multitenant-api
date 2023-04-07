package com.yuriserka.template.core.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer
import io.github.resilience4j.common.retry.configuration.RetryConfigCustomizer
import java.time.Duration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CircuitBreakerConfiguration {

  @Bean
  fun circuitBreakerConfigCustomizer(): CircuitBreakerConfigCustomizer {
    return CircuitBreakerConfigCustomizer.of(
        "default-circuit-breaker"
    ) { builder ->
        builder
            .slidingWindowType(SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(10)
            .failureRateThreshold(50.0f)
            .slowCallRateThreshold(10.0f)
            .slowCallDurationThreshold(Duration.ofSeconds(1))
            .waitDurationInOpenState(Duration.ofSeconds(5))
    }
  }

  @Bean
  fun retryConfigCustomizer(): RetryConfigCustomizer {
    return RetryConfigCustomizer.of(
        "default-retry"
    ) { builder ->
        builder.failAfterMaxAttempts(true).maxAttempts(5).waitDuration(Duration.ofSeconds(5))
    }
  }
}
