package com.bht.meditrack.Patientenverwaltung.application.services;

import com.bht.meditrack.Patientenverwaltung.domain.events.PatientEntferntEvent;
import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.meditrack.Patientenverwaltung.infrastructure.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public PatientService(PatientRepository patientRepository, ApplicationEventPublisher eventPublisher) {
        this.patientRepository = Objects.requireNonNull(patientRepository, "Repository darf nicht null sein.");
        this.eventPublisher = Objects.requireNonNull(eventPublisher, "EventPublisher darf nicht null sein.");
    }

    public Optional<Patient> findById(UUID id) {
        return patientRepository.findById(id);
    }

    public Patient upsertPatient(UUID patientId, Patient patient) {
        validateInput(patientId, patient);
        return Optional.of(patient)
                .map(this::savePatient)
                .map(this::publishPatientEvent)
                .orElseThrow(() -> new RuntimeException("Fehler beim Speichern des Patienten"));
    }

    private void validateInput(UUID patientId, Patient patient) {
        validatePatientId(patientId);
        validatePatient(patient);
    }

    private void validatePatientId(UUID patientId) {
        if (patientId == null) {
            throw new IllegalArgumentException("PatientId darf nicht null sein.");
        }
    }

    private void validatePatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient darf nicht null sein.");
        }
    }

    private Patient savePatient(Patient patient) {
        return Optional.ofNullable(patientRepository.save(patient))
                .orElseThrow(() -> new RuntimeException("Fehler beim Speichern des Patienten"));
    }

    private Patient publishPatientEvent(Patient savedPatient) {
        // Publish an event if needed
        return savedPatient;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> findByKrankenkasse(String krankenkasse) {
        return patientRepository.findByKrankenkasse(new Krankenkasse(krankenkasse));
    }

    public void deletePatient(UUID patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isEmpty()) {
            throw new IllegalArgumentException(String.format("Patient mit ID %s nicht gefunden", patientId));
        }
        patientRepository.deleteById(patientId);
        eventPublisher.publishEvent(new PatientEntferntEvent(patientId));
    }
}
