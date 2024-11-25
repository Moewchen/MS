package com.bht.MediTrack.Patientenverwaltung.infrastructure.repositories;

import com.bht.MediTrack.Patientenverwaltung.domain.events.PatientAngelegtEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PatientEventListener {
    @EventListener
    public void handlePatientAngelegt(PatientAngelegtEvent event) {
        System.out.println("Neuer Patient angelegt: ID ="
                + event.getPatientId() +
                ", Personendata =" + event.getPersonendaten() +
                ", Kontaktdaten =" + event.getKontaktdaten() +
                ", Krankenkasse =" + event.getKrankenkasse() +
                ", Krankenversicherungsnummer =" + event.getKrankenversicherungsnummer() +
                ", Adresse =" + event.getAdresse() +
                ", Angelegt am =" + event.getCreatedAt());
    }
}
