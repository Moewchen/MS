package com.bht.meditrack.Patientenverwaltung.infrastructure.persistence;

import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;

public class PatientMapper {
    public static PatientEntity toPatientEntity(Patient patient) {
        PatientEntity entity = new PatientEntity();
        entity.setId(patient.getId());
        return entity;
    }

    public static Patient toPatientDomain(PatientEntity entity) {
        Patient vitaldaten = new Patient();
        vitaldaten.setId(entity.getId());
        return vitaldaten;
    }
}
