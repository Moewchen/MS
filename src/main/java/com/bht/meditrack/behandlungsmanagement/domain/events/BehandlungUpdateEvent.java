package com.bht.meditrack.behandlungsmanagement.domain.events;

import java.util.UUID;


public class BehandlungUpdateEvent {

    private final UUID id;
    private final String beschreibung;



    public BehandlungUpdateEvent(UUID id, String beschreibung) {
        this.id = id;
        this.beschreibung = beschreibung;
    }

    public UUID getId() {
        return id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

}