package com.yuriserka.multitenant.api.performance

import org.aspectj.lang.annotation.Aspect
import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@Aspect
@EnableAspectJAutoProxy
class PerformanceInterceptor {
  @Bean
  fun performanceMonitorAdvisor(): Advisor {
    val pointcut = AspectJExpressionPointcut()
    val pointcutExpression: String =
        "within(@org.springframework.web.bind.annotation.RestController *)" +
            " || within(@org.springframework.stereotype.Repository *)"
    pointcut.expression = pointcutExpression
    return DefaultPointcutAdvisor(pointcut, InterceptorToInfo(true))
  }
}
