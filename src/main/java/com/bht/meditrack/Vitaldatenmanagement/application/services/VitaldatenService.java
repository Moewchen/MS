package com.bht.meditrack.Vitaldatenmanagement.application.services;

import com.bht.meditrack.Vitaldatenmanagement.domain.events.VitaldatenErstelltEvent;
import com.bht.meditrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import com.bht.meditrack.Vitaldatenmanagement.infrastructure.repositories.VitaldatenRepository;
import com.bht.meditrack.PublisherEvent;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Vitaldaten> findById(UUID id) {
        return vitaldatenRepository.findById(id);
    }

    @Autowired  // Constructor injection → das andere oben reicht, ist hier jetzt gedoppelt
    public VitaldatenService(VitaldatenRepository vitaldatenRepository, PublisherEvent eventPublisher) {
        this.vitaldatenRepository = vitaldatenRepository;
        this.eventListener = eventPublisher;
    }

    public Vitaldaten upsertVitaldaten(UUID patientId, final Vitaldaten vitaldaten) {
        if (vitaldaten == null || vitaldaten.getHerzfrequenz() < 30 || vitaldaten.getHerzfrequenz() > 200) {
            throw new InvalidVitaldatenException("Invalid Herzfrequenz value");
        }

        if (patientId == null) {
            throw new InvalidVitaldatenException("PatientId cannot be null");
        }
        if (vitaldaten.getId() != null) {
            Optional<Vitaldaten> existingVitaldaten = vitaldatenRepository.findById(vitaldaten.getId());
            if (existingVitaldaten.isEmpty()) {
                throw new VitaldatenNotFoundException("Vitaldaten not found");
            }
        }

        Vitaldaten savedVitaldaten = vitaldatenRepository.save(vitaldaten);
        Objects.requireNonNull(savedVitaldaten, "Failed to save Vitaldaten");

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

    public List<Vitaldaten> findByPatientId(UUID patientId) {
        return vitaldatenRepository.findByPatientId(patientId);
    }

public void deleteVitaldaten(UUID patientId, UUID vitaldatenId) {
    Optional<Vitaldaten> optionalVitaldaten = vitaldatenRepository.findById(vitaldatenId);
    if (optionalVitaldaten.isEmpty() || !optionalVitaldaten.get().getPatient().getId().equals(patientId)) {
        throw new VitaldatenNotFoundException("Vitaldaten with ID " + vitaldatenId + " not found for patient with ID " + patientId);
    }
    vitaldatenRepository.deleteByPatientIdAndId(patientId, vitaldatenId);
}

}
