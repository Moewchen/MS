package com.bht.meditrack.Behandlungsmanagement.domain.model;


import com.bht.meditrack.Arztverwaltung.domain.model.Arzt;
import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Behandlung {


    private UUID id;
    private String beschreibung;
    private UUID patient;
    private Arzt arzt;


    // Standard-Konstruktor
    public Behandlung() {
    }

    // Konstruktor mit Parametern
    public Behandlung(UUID id, String beschreibung, Patient patient, Arzt arzt) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.patient = patient.getId();
        this.arzt = arzt;
    }

    // Getter und Setter Methoden
  public UUID getPatientId() {
    return patient;
}
}
