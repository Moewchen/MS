package com.bht.MediTrack.Patientenverwaltung.infrastructure.repositories;

import com.bht.MediTrack.Patientenverwaltung.domain.events.PatientAngelegtEvent;
import com.bht.MediTrack.Patientenverwaltung.domain.events.PatientEntferntEvent;
import com.bht.MediTrack.Patientenverwaltung.domain.events.PatientUpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PatientEventListener {

    @EventListener
    public void handlePatientAngelegt(PatientAngelegtEvent event) {
        System.out.println("Neuer Patient angelegt: ID = " + event.getPatientId() +
                ", Personendaten = " + event.getPersonendaten() +
                ", Kontaktdaten = " + event.getKontaktdaten() +
                ", Krankenkasse = " + event.getKrankenkasse() +
                ", Krankenversicherungsnummer = " + event.getKrankenversicherungsnummer() +
                ", Adresse = " + event.getAdresse() +
                ", Angelegt am = " + event.getCreatedAt());
    }

    @EventListener
    public void handlePatientEntfernt(PatientEntferntEvent event) {
        System.out.println("Patient entfernt: ID = " + event.getPatientId() +
                ", Entfernt am = " + event.getDeletedAt());
    }

    @EventListener
    public void handlePatientUpdate(PatientUpdateEvent event) {
        System.out.println("Patient aktualisiert: ID = " + event.getPatientId() +
                ", Aktualisiertes Feld = " + event.getUpdatedField() +
                ", Neuer Wert = " + event.getUpdatedValue() +
                ", Aktualisiert am = " + event.getUpdatedAt());
    }
}
