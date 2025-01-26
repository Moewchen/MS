package com.bht.meditrack.behandlungsmanagement.domain.model;


import com.bht.meditrack.arztverwaltung.domain.model.Arzt;
import com.bht.meditrack.patientenverwaltung.domain.model.Patient;
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
