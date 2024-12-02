package com.bht.MediTrack.Behandlungsmanagement.domain.events;

import com.bht.MediTrack.Arztverwaltung.domain.model.Arzt;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;

import java.util.UUID;


public class BehandlungErstelltEvent {

    private final UUID id;
    private final String beschreibung;
    private final Patient patient;
    private final Arzt arzt;
    private final Vitaldaten vitaldaten;


    public BehandlungErstelltEvent(UUID id, String beschreibung, Patient patient, Arzt arzt, Vitaldaten vitaldaten) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.patient = patient;
        this.arzt = arzt;
        this.vitaldaten = vitaldaten;
    }

    public UUID getId() {
        return id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Patient getPatient() {
        return patient;
    }

    public Arzt getArzt() {
        return arzt;
    }

    public Vitaldaten getVitaldaten() {
        return vitaldaten;
    }
}