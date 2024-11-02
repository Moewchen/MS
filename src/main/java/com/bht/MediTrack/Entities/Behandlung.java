package com.bht.MediTrack.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class Behandlung {

    @Id
    private UUID id;

    private String beschreibung;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "arzt_id")
    private Arzt arzt;

    @ManyToOne
    @JoinColumn(name = "vitaldaten_id")
    private Vitaldaten vitaldaten;

    //Standard-Konstruktor für JPA
    public Behandlung() {
    }

    // Konstruktor
    public Behandlung(UUID id, String beschreibung, Patient patient, Arzt arzt){
        this.id = id;
        this.beschreibung = beschreibung;
        this.patient = patient;
        this.arzt = arzt;
    }
    // Methoden zur Verwaltung der Vitaldaten
    public void setVitaldaten(Vitaldaten vitaldaten) {
        this.vitaldaten = vitaldaten;
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
}
