package com.bht.meditrack.behandlungsmanagement.domain.events;

import com.bht.meditrack.arztverwaltung.domain.model.Arzt;

import java.util.UUID;


public class BehandlungErstelltEvent {

    private final UUID id;
    private final String beschreibung;
    private final UUID patient;
    private final Arzt arzt;


    public BehandlungErstelltEvent(UUID id, String beschreibung, UUID patient, Arzt arzt) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.patient = patient;
        this.arzt = arzt;
    }

    public UUID getId() {
        return id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public UUID getPatient() {
        return patient;
    }

    public Arzt getArzt() {
        return arzt;
    }

}