// PatientEntferntEvent.java
package com.bht.MediTrack.Patientenverwaltung.domain.events;

import java.time.Instant;
import java.util.UUID;

public class PatientEntferntEvent {
    private final UUID patientId;
    private final Instant deletedAt;

    public PatientEntferntEvent(UUID patientId) {
        this.patientId = patientId;
        this.deletedAt = Instant.now();
    }

    public UUID getPatientId() {
        return patientId;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
