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

                if (vitaldaten.getHerzfrequenz() < 60 || vitaldaten.getHerzfrequenz() > 80) {
                    throw new InvalidVitaldatenException("Invalid Herzfrequenz value: " + vitaldaten.getHerzfrequenz());
                }
                if (vitaldaten.getAtemfrequenz() < 0 || vitaldaten.getAtemfrequenz() > 70) {
                    throw new InvalidVitaldatenException("Invalid Atemfrequenz value: " + vitaldaten.getAtemfrequenz());
                }
                if (vitaldaten.getSystolisch() < 110 || vitaldaten.getSystolisch() > 130) {
                    throw new InvalidVitaldatenException("Invalid Systolisch value: " + vitaldaten.getSystolisch());
                }
                if (vitaldaten.getDiastolisch() < 0 || vitaldaten.getDiastolisch() > 170) {
                    throw new InvalidVitaldatenException("Invalid Diastolisch value: " + vitaldaten.getDiastolisch());
                }
                if (vitaldaten.getTemperatur() < 34 || vitaldaten.getTemperatur() > 45) {
                    throw new InvalidVitaldatenException("Invalid Temperatur value: " + vitaldaten.getTemperatur());
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
