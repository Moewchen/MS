package com.bht.meditrack.Patientenverwaltung.infrastructure.repositories;

import com.bht.meditrack.Patientenverwaltung.domain.events.PatientAngelegtEvent;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PatientEventListener {
    @EventListener
    public void handlePatientAngelegt(PatientAngelegtEvent event) {
        Optional.ofNullable(event)
                .ifPresent(e -> log.info("Neuer Patient angelegt: ID={}, Personendata={}, " +
                                "Kontaktdaten={}, Krankenkasse={}, Krankenversicherungsnummer={}, " +
                                "Adresse={}, Angelegt am={}",
                        e.getPatientId(),
                        e.getPersonendaten(),
                        e.getKontaktdaten(),
                        e.getKrankenkasse(),
                        e.getKrankenversicherungsnummer(),
                        e.getAdresse(),
                        e.getCreatedAt()
                ));
    }
}
