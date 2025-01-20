package com.bht.meditrack.Patientenverwaltung.application.services;


import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.Patientenverwaltung.infrastructure.persistence.PatientEntity;
import com.bht.meditrack.Patientenverwaltung.infrastructure.repositories.PatientRepository;
import com.bht.meditrack.PublisherEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PublisherEvent eventPublisher;

    @Autowired // Constructor injection
    public PatientService(PatientRepository patientRepository, PublisherEvent eventPublisher) {
        this.patientRepository = Objects.requireNonNull(patientRepository, "Repository darf nicht null sein.");
        this.eventPublisher = eventPublisher;
    }

    // Methode, um Patienten anhand der ID zu finden
    public Optional<Patient> findById(UUID id) {
        return patientRepository.findById(id)
                .map(this::toDomainModel); // Umwandlung von Entity zu Domain
    }

    // Methode zum Erstellen oder Aktualisieren eines Patienten
    public Optional<Patient> upsertPatient(UUID patientId, Patient patient) {
        validateInput(patientId, patient);

        return Optional.of(patient)
                .map(this::toEntity)         // Umwandlung von Domain zu Entity
                .map(patientRepository::save)
                .map(this::toDomainModel)    // Rückwandlung von Entity zu Domain
                .map(this::publishPatientEvent);
    }

    // Validierungen
    private void validateInput(UUID patientId, Patient patient) {
        if (patientId == null) {
            throw new IllegalArgumentException("PatientId darf nicht null sein.");
        }
        if (patient == null) {
            throw new IllegalArgumentException("Patient darf nicht null sein.");
        }
        // Weitere Validierungen (falls erforderlich) können hier hinzugefügt werden.
    }

    // Konvertierung von Domänenmodell zu Persistenzmodell (Entity)
    private PatientEntity toEntity(Patient patient) {
        PatientEntity entity = new PatientEntity();
        entity.setId(patient.getId());
        entity.setKrankenkasse(patient.getKrankenkasse());
        entity.setKrankenversicherungsnummer(patient.getKrankenversicherungsnummer());
        entity.setPersonendaten(patient.getPersonendaten());
        entity.setKontaktdaten(patient.getKontaktdaten());
        entity.setAdresse(patient.getAdresse());
        return entity;
    }

    // Konvertierung von Persistenzmodell (Entity) zurück in Domänenmodell
    private Patient toDomainModel(PatientEntity entity) {
        return new Patient(
                entity.getId(),
                entity.getKrankenkasse(),
                entity.getKrankenversicherungsnummer(),
                entity.getPersonendaten(),
                entity.getKontaktdaten(),
                entity.getAdresse(),
                entity.getVitaldaten()
        );
    }

    // Speichern der Patientendaten und eventuelle Veröffentlichung
    private Patient publishPatientEvent(Patient savedPatient) {
        // Hier könnte ein Event erstellt und veröffentlicht werden (z.B. PatientErstelltEvent)
        // Beispiel:
        // PatientErstelltEvent event = new PatientErstelltEvent(savedPatient.getId(), ...);
        // eventPublisher.publishEvent(event);
        return savedPatient;
    }

    // Abrufen aller Patienten
    public List<Patient> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::toDomainModel)
                .toList();
    }

    // Löschen eines Patienten
    public void deletePatient(UUID patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new IllegalArgumentException("Patient nicht gefunden");
        }
        patientRepository.deleteById(patientId);
    }
}



