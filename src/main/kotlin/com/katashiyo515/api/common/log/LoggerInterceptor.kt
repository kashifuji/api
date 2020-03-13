package com.katashiyo515.api.common.log

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
@Component
class LoggerInterceptor {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    @Pointcut("within(com.katashiyo515.api.application.controller.*)")
    fun controllerLog() {}

    @Pointcut("within(com.katashiyo515.api.infrastructure.repository.*)")
    fun repositoryLog() {}

    @Around("controllerLog() || repositoryLog()")
    fun aroundRepository(pjp: ProceedingJoinPoint): Any? {
        val stopWatch = StopWatch()

        stopWatch.start()
        try {
            return pjp.proceed()
        } finally {
            stopWatch.stop()
            log.info("{} {} ms", pjp.signature, stopWatch.totalTimeMillis)
        }
    }
}
