package com.bht.MediTrack.Behandlungsmanagement.domain.model;


import com.bht.MediTrack.Arztverwaltung.domain.model.Arzt;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;

import java.util.UUID;


public class Behandlung {


    private UUID id;
    private String beschreibung;
    private Patient patient;
    private Arzt arzt;
    private Vitaldaten vitaldaten;

    // Standard-Konstruktor
    public Behandlung() {
    }

    // Konstruktor mit Parametern
    public Behandlung(UUID id, String beschreibung, Patient patient, Arzt arzt) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.patient = patient;
        this.arzt = arzt;
    }

    // Getter und Setter Methoden
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Arzt getArzt() {
        return arzt;
    }

    public void setArzt(Arzt arzt) {
        this.arzt = arzt;
    }

    public Vitaldaten getVitaldaten() {
        return vitaldaten;
    }

    public void setVitaldaten(Vitaldaten vitaldaten) {
        this.vitaldaten = vitaldaten;
    }

    public UUID getPatientId() {
        return patient.getId();
    }
}
