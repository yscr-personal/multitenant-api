package com.yuriserka.multitenant.api.performance

import org.aopalliance.intercept.MethodInvocation
import org.apache.commons.logging.Log
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor
import org.springframework.util.StopWatch

class InterceptorToInfo(useDynamicLogger: Boolean) : AbstractMonitoringInterceptor() {

  init {
    setUseDynamicLogger(useDynamicLogger)
  }

  override fun invokeUnderTrace(invocation: MethodInvocation, log: Log): Any? {
    val name = createInvocationTraceName(invocation)
    val stopWatch = StopWatch(name)
    stopWatch.start(name)
    try {
      return invocation.proceed()
    } finally {
      stopWatch.stop()
      log.info(stopWatch.shortSummary())
    }
  }

  override fun invoke(invocation: MethodInvocation): Any? {
    val logger = getLoggerForInvocation(invocation)
    return invokeUnderTrace(invocation, logger)
  }
}
