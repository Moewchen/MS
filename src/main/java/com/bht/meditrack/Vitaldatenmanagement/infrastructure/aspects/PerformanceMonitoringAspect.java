package com.bht.meditrack.Vitaldatenmanagement.infrastructure.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class PerformanceMonitoringAspect {
    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

    // Performance-Monitoring für Repository (Datenbank)
    // rund um die Ausführung einer Methode
    @Around("execution(* com.bht.meditrack.Vitaldatenmanagement.infrastructure.repositories.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        //Speicherung in Log-Datei
        logger.info("Performance: {}.{} - {} ms",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                endTime - startTime
        );

        return result;
    }
}
