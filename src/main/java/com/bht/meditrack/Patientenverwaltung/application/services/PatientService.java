package com.bht.meditrack.Patientenverwaltung.application.services;
import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.Patientenverwaltung.infrastructure.repositories.PatientRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PatientService {


    private final PatientRepository patientRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired // Constructor injection
    public PatientService(PatientRepository patientRepository, ApplicationEventPublisher eventPublisher) {
        this.patientRepository = Objects.requireNonNull(patientRepository, "Repository darf nicht null sein.");
        this.eventPublisher = eventPublisher;
    }


    public Patient upsertPatient(UUID patientId, final Patient patient) {
        if (patientId == null) {
            throw new IllegalArgumentException("PatientId darf nicht null sein.");
        }
        if (patient == null) {
            throw new IllegalArgumentException("Patient darf nicht null sein.");
        }
        patient.setId(patientId);
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {

        return (List<Patient>) patientRepository.findAll();
    }

    public Optional<Patient> findById(UUID id) {
        return patientRepository.findById(id);
    }

}
