package com.bht.MediTrack.Entities;

public class Behandlung {
    private String id;
    private String beschreibung;
    private Patient patient;
    private Arzt arzt;
    private Vitaldaten vitaldaten;

    // Konstruktor
    public Behandlung(String id, String beschreibung, Patient patient, Arzt arzt){
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
    public String getId() {
        return id;
    }
    public void setId(String id) {
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
