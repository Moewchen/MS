package com.bht.meditrack.patientenverwaltung.domain.valueojects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Krankenkasse(String krankenkasse) {

    public Krankenkasse {
        if (krankenkasse == null || krankenkasse.trim().isEmpty()) {
            throw new IllegalArgumentException("Krankenkasse darf nicht leer sein");
        }
        if (!krankenkasse.matches("^[A-Za-z]+$")) {
            throw new IllegalArgumentException("Krankenkasse darf nur Buchstaben enthalten");
        }
    }
}
