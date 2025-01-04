package com.bht.meditrack.Patientenverwaltung.domain.events;

import java.time.Instant;
import java.util.UUID;

public class PatientUpdateEvent {
    private final UUID patientId;
    private final String updatedField;
    private final String updatedValue;
    private final Instant updatedAt;

    public PatientUpdateEvent(UUID patientId, String updatedField, String updatedValue) {
        this.patientId = patientId;
        this.updatedField = updatedField;
        this.updatedValue = updatedValue;
        this.updatedAt = Instant.now();
    }

    public UUID getPatientId() {
        return patientId;
    }

    public String getUpdatedField() {
        return updatedField;
    }

    public String getUpdatedValue() {
        return updatedValue;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
