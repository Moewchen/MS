package com.bht.meditrack.Vitaldatenmanagement.infrastructure.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    //Vor der Ausführung einer Methode
    @Before("execution(* com.bht.meditrack.Vitaldatenmanagement.infrastructure.repositories.*.*(..))")
    public void logBeforeServiceMethod(JoinPoint joinPoint) {
        logger.info("Aufruf von: {}", joinPoint.getSignature().getName());
    }

    //Wenn eine Methode eine Ausnahme wirft
    @AfterThrowing(
            pointcut = "execution(* com.bht.meditrack.Vitaldatenmanagement.infrastructure.repositories.*.*(..))",
            throwing = "error"
    )

    //Speicherung in Log-Datei
    public void logServiceErrors(JoinPoint joinPoint, Throwable error) {
        logger.error("Fehler in {}: {}",
                joinPoint.getSignature().getName(),
                error.getMessage()
        );
    }
}
