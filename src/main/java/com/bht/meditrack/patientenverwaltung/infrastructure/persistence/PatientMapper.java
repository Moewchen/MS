package com.bht.meditrack.patientenverwaltung.infrastructure.persistence;

import com.bht.meditrack.patientenverwaltung.domain.model.Patient;

public final class PatientMapper {
    private PatientMapper() {
    }

    public static PatientEntity toPatientEntity(Patient patient) {
        PatientEntity entity = new PatientEntity();
        entity.setId(patient.getId());
        entity.setKrankenkasse(patient.getKrankenkasse());
        entity.setKrankenversicherungsnummer(patient.getKrankenversicherungsnummer());
        entity.setPersonendaten(patient.getPersonendaten());
        entity.setKontaktdaten(patient.getKontaktdaten());
        entity.setAdresse(patient.getAdresse());

        return entity;
    }

    public static Patient toPatientDomain(PatientEntity entity) {
        Patient patient = new Patient();
        patient.setId(entity.getId());
        patient.setKrankenkasse(entity.getKrankenkasse());
        patient.setKrankenversicherungsnummer(entity.getKrankenversicherungsnummer());
        patient.setPersonendaten(entity.getPersonendaten());
        patient.setKontaktdaten(entity.getKontaktdaten());
        patient.setAdresse(entity.getAdresse());

        return patient;
    }
}