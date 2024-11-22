package com.bht.MediTrack.Arztverwaltung.domain.valueojects;

//ToDo: Auswahlliste integrieren
public record Fachrichtung (String fachrichtung){

    //Konstruktor mit Prüfung
    //ToDo: Prüfungen vervollständigen
    public Fachrichtung{
        if (fachrichtung == null || fachrichtung.trim().isEmpty()) {
        throw new IllegalArgumentException("Fachrichtung darf nicht leer sein");
        }
    }

}
