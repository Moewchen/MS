package com.bht.MediTrack.Patientenverwaltung.domain.valueojects;

import jakarta.persistence.Embeddable;

@Embeddable
//ToDo: Auswahlliste integrieren
public record Krankenkasse (String krankenkasse){

    //Konstruktor mit Prüfung
    //ToDo: Prüfungen vervollständigen
    public Krankenkasse {
        if (krankenkasse == null || krankenkasse.trim().isEmpty()) {
            throw new IllegalArgumentException("Krankenkasse darf nicht leer sein");
        }


    }

}
