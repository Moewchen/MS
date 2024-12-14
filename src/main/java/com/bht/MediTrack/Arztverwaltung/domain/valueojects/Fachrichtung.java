package com.bht.MediTrack.Arztverwaltung.domain.valueojects;

import jakarta.persistence.Embeddable;

//TODO WAS WAR DER PLAN?
//ToDo: Auswahlliste integrieren
@Embeddable
public record Fachrichtung (String fachrichtung){

    //Konstruktor mit Prüfung
    //ToDo: Prüfungen vervollständigen
    public Fachrichtung{
        if (fachrichtung == null || fachrichtung.trim().isEmpty()) {
        throw new IllegalArgumentException("Fachrichtung darf nicht leer sein");
        }
    }

}


/*
public enum Fachrichtung {
    ALLGEMEINMEDIZIN,
    KARDIOLOGIE,
    DERMATOLOGIE,
    ZAHNARZT
}

 */
