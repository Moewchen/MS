package com.bht.meditrack.arztverwaltung.domain.valueojects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Fachrichtung (String fachrichtung){

    //Konstruktor mit Prüfung
    public Fachrichtung{
        if (fachrichtung == null || fachrichtung.trim().isEmpty()) {
        throw new IllegalArgumentException("Fachrichtung darf nicht leer sein");
        }
    }

}
