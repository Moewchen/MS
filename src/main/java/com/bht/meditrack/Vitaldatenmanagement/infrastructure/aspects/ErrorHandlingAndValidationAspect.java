package com.bht.meditrack.Vitaldatenmanagement.infrastructure.aspects;

import com.bht.meditrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import com.bht.meditrack.Vitaldatenmanagement.exceptions.InvalidVitaldatenException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorHandlingAndValidationAspect {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingAndValidationAspect.class);

    @Around("execution(* com.bht.meditrack.Vitaldatenmanagement.application.services.*.*(..))")
    public Object handleError(ProceedingJoinPoint joinPoint) throws Throwable {

        // Validierung vor der Ausführung der Methode
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof Vitaldaten) {
                Vitaldaten vitaldaten = (Vitaldaten) arg;

                if (vitaldaten == null || vitaldaten.getHerzfrequenz() < 30 || vitaldaten.getHerzfrequenz() > 200) {
                    throw new InvalidVitaldatenException("Invalid Herzfrequenz value: " + vitaldaten.getHerzfrequenz());
                }
                if (vitaldaten.getPatient() == null || vitaldaten.getPatient().getId() == null) {
                    throw new InvalidVitaldatenException("Patient cannot be null");
                }

            }
        }
        try {
            return joinPoint.proceed();
        }
        catch (Exception exception) {
            logger.info("ErrorHandling {} : {}",
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
