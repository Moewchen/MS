package com.bht.meditrack.vitaldatenmanagement.infrastructure.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// errorhandling
// Fehlerbehandlung von der eigentlichen Geschäftslogik trennen
@Aspect
@Component
public class ErrorHandlingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingAspect.class);

    // errorhandling für Services
    // rund um die Ausführung einer Methode
    @Around("execution(* com.bht.meditrack.vitaldatenmanagement.application.services.*.*(..))")
    public Object handleError(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        }
        catch (Exception exception) {
            //Speicherung in Log-Datei
            logger.info("errorhandling {} : {}",
                    joinPoint.getSignature(),
                    exception.getMessage()
            );
            String errorMessage = String.format(
                    "Fehler in Methode %s mit Argumenten %s: %s",
                    joinPoint.getSignature(),
                    joinPoint.getArgs(),
                    exception.getMessage()
            );
            logger.error(errorMessage, exception);
            throw new RuntimeException(errorMessage, exception);
        }
    }
}