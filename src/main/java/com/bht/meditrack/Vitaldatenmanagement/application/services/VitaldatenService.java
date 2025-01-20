package com.bht.meditrack.Vitaldatenmanagement.application.services;

import com.bht.meditrack.Vitaldatenmanagement.domain.events.VitaldatenErstelltEvent;
import com.bht.meditrack.Vitaldatenmanagement.domain.model.Vitaldaten;

import com.bht.meditrack.Vitaldatenmanagement.infrastructure.persistence.VitaldatenEntity;
import com.bht.meditrack.Vitaldatenmanagement.infrastructure.repositories.VitaldatenRepository;
import com.bht.meditrack.PublisherEvent;
import org.springframework.stereotype.Service;
import com.bht.meditrack.Vitaldatenmanagement.exceptions.InvalidVitaldatenException;
import com.bht.meditrack.Vitaldatenmanagement.exceptions.VitaldatenNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class VitaldatenService {

    private final PublisherEvent eventListener;
    private final VitaldatenRepository vitaldatenRepository;

    private static final int MIN_HERZFREQUENZ = 20;
    private static final int MAX_HERZFREQUENZ = 220;
    private static final int MIN_ATEMFREQUENZ = 5;
    private static final int MAX_ATEMFREQUENZ = 60;
    private static final int MIN_SYSTOLISCH = 50;
    private static final int MAX_SYSTOLISCH = 250;
    private static final int MIN_DIASTOLISCH = 40;
    private static final int MAX_DIASTOLISCH = 150;
    private static final float MIN_TEMPERATUR = 30.0f;
    private static final float MAX_TEMPERATUR = 45.0f;

    public VitaldatenService(VitaldatenRepository vitaldatenRepository, PublisherEvent eventPublisher) {
        this.vitaldatenRepository = Objects.requireNonNull(vitaldatenRepository, "Repository darf nicht null sein.");
        this.eventListener = Objects.requireNonNull(eventPublisher, "EventPublisher darf nicht null sein.");
    }

    // Methode, um Vitaldaten anhand der ID zu finden
    public Optional<Vitaldaten> findById(UUID id) {
        return vitaldatenRepository.findById(id)
                .map(this::toDomainModel);  // Umwandlung von Entity zu Domain
    }

    // Methode zum Erstellen oder Aktualisieren von Vitaldaten
    public Optional<Vitaldaten> upsertVitaldaten(UUID patientId, Vitaldaten vitaldaten) {
        validateInput(patientId, vitaldaten);
        validateExistingVitaldaten(vitaldaten);

        // Speichern und Event veröffentlichen
        return Optional.of(vitaldaten)
                .map(this::toEntity)       // Umwandlung von Domain zu Entity
                .map(vitaldatenRepository::save)
                .map(this::toDomainModel)  // Rückwandlung von Entity zu Domain
                .map(this::publishVitaldatenEvent);
    }

    // Validierungen
    private void validateInput(UUID patientId, Vitaldaten vitaldaten) {
        validatePatientId(patientId);
        validateHerzfrequenz(vitaldaten.getHerzfrequenz());
        validateAtemfrequenz(vitaldaten.getAtemfrequenz());
        validateSystolisch(vitaldaten.getSystolisch());
        validateDiastolisch(vitaldaten.getDiastolisch());
        validateTemperatur(vitaldaten.getTemperatur());
        validateVitaldaten(vitaldaten);
    }

    private void validatePatientId(UUID patientId) {
        if (patientId == null) {
            throw new InvalidVitaldatenException("PatientId darf nicht null sein");
        }
    }

    private void validateVitaldaten(Vitaldaten vitaldaten) {
        if (vitaldaten == null) {
            throw new InvalidVitaldatenException("Vitaldaten dürfen nicht null sein");
        }
    }

    // Validierungslogik für einzelne Felder (Herzfrequenz, Atemfrequenz, etc.)
    private void validateHerzfrequenz(short herzfrequenz) {
        if (herzfrequenz < MIN_HERZFREQUENZ || herzfrequenz > MAX_HERZFREQUENZ) {
            throw new InvalidVitaldatenException(
                    String.format("Herzfrequenz muss zwischen %d und %d liegen, war: %d",
                            MIN_HERZFREQUENZ, MAX_HERZFREQUENZ, herzfrequenz)
            );
        }
    }

    private void validateAtemfrequenz(byte atemfrequenz) {
        if (atemfrequenz < MIN_ATEMFREQUENZ || atemfrequenz > MAX_ATEMFREQUENZ) {
            throw new InvalidVitaldatenException(
                    String.format("Atemfrequenz muss zwischen %d und %d liegen, war: %d",
                            MIN_ATEMFREQUENZ, MAX_ATEMFREQUENZ, atemfrequenz)
            );
        }
    }

    private void validateSystolisch(short systolisch) {
        if (systolisch < MIN_SYSTOLISCH || systolisch > MAX_SYSTOLISCH) {
            throw new InvalidVitaldatenException(
                    String.format("Systolisch muss zwischen %d und %d liegen, war: %d",
                            MIN_SYSTOLISCH, MAX_SYSTOLISCH, systolisch)
            );
        }
    }

    private void validateDiastolisch(short diastolisch) {
        if (diastolisch < MIN_DIASTOLISCH || diastolisch > MAX_DIASTOLISCH) {
            throw new InvalidVitaldatenException(
                    String.format("Diastolisch muss zwischen %d und %d liegen, war: %d",
                            MIN_DIASTOLISCH, MAX_DIASTOLISCH, diastolisch)
            );
        }
    }

    private void validateTemperatur(float temperatur) {
        if (temperatur < MIN_TEMPERATUR || temperatur > MAX_TEMPERATUR) {
            throw new InvalidVitaldatenException(
                    String.format("Temperatur muss zwischen %.1f und %.1f liegen, war: %.1f",
                            MIN_TEMPERATUR, MAX_TEMPERATUR, temperatur)
            );
        }
    }

    // Überprüfen, ob die Vitaldaten bereits existieren
    private void validateExistingVitaldaten(Vitaldaten vitaldaten) {
        if (vitaldaten.getId() != null && !vitaldatenRepository.findById(vitaldaten.getId()).isPresent()) {
            throw new VitaldatenNotFoundException(
                    String.format("Vitaldaten mit ID %s nicht gefunden", vitaldaten.getId())
            );
        }
    }

    // Konvertierung von Domänenmodell zu Persistenzmodell (Entity)
    private VitaldatenEntity toEntity(Vitaldaten vitaldaten) {
        VitaldatenEntity entity = new VitaldatenEntity();
        entity.setId(vitaldaten.getId());
        entity.setHerzfrequenz(vitaldaten.getHerzfrequenz());
        entity.setAtemfrequenz(vitaldaten.getAtemfrequenz());
        entity.setSystolisch(vitaldaten.getSystolisch());
        entity.setDiastolisch(vitaldaten.getDiastolisch());
        entity.setTemperatur(vitaldaten.getTemperatur());
        entity.setDatum(vitaldaten.getDatum());
        return entity;
    }

    // Konvertierung von Persistenzmodell (Entity) zurück in Domänenmodell
    private Vitaldaten toDomainModel(VitaldatenEntity entity) {
        return new Vitaldaten(
                entity.getId(),
                entity.getHerzfrequenz(),
                entity.getAtemfrequenz(),
                entity.getSystolisch(),
                entity.getDiastolisch(),
                entity.getTemperatur(),
                entity.getDatum()
        );
    }

    // Speichern der Vitaldaten und eventuelle Veröffentlichung
    private Vitaldaten publishVitaldatenEvent(Vitaldaten savedVitaldaten) {
        VitaldatenErstelltEvent event = new VitaldatenErstelltEvent(
                savedVitaldaten.getId(),
                savedVitaldaten.getHerzfrequenz(),
                savedVitaldaten.getAtemfrequenz(),
                savedVitaldaten.getSystolisch(),
                savedVitaldaten.getDiastolisch(),
                savedVitaldaten.getTemperatur(),
                LocalDateTime.now()
        );
        eventListener.publishEvent(event);
        return savedVitaldaten;
    }

    // Finden der Vitaldaten für einen bestimmten Patienten
    public List<Vitaldaten> findByPatientId(UUID patientId) {
        return vitaldatenRepository.findByPatientId(patientId)
                .stream()
                .map(this::toDomainModel)
                .toList();
    }

    // Löschen der Vitaldaten eines Patienten
    public void deleteVitaldaten(UUID patientId, UUID vitaldatenId) {
        Optional<VitaldatenEntity> optionalEntity = vitaldatenRepository.findById(vitaldatenId);
        if (optionalEntity.isEmpty() || !optionalEntity.get().getPatient().getId().equals(patientId)) {
            throw new VitaldatenNotFoundException(
                    String.format("Vitaldaten mit ID %s nicht gefunden für Patient mit ID %s", vitaldatenId, patientId)
            );
        }
        vitaldatenRepository.deleteByPatientIdAndId(patientId, vitaldatenId);
    }
}
