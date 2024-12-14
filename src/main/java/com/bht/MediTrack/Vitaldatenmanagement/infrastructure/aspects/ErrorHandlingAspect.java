package com.bht.MediTrack.Vitaldatenmanagement.infrastructure.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// ErrorHandling
// Fehlerbehandlung von der eigentlichen Geschäftslogik trennen
@Aspect
@Component
public class ErrorHandlingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingAspect.class);

    // ErrorHandling für Services
    // rund um die Ausführung einer Methode
    @Around("execution(* com.bht.MediTrack.Vitaldatenmanagement.application.services.*.*(..))")
    public Object handleError(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
           // Object result = joinPoint.proceed();
            //return result;
            return joinPoint.proceed();
        }
        //Exception exception = (Exception) result;
        catch (Exception exception) {
            //Speicherung in Log-Datei
            logger.info("ErrorHandling {} : {}",
                    joinPoint.getSignature(),
                    exception.getMessage()
            );
            throw exception;
        }
    }
}
