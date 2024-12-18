package com.bht.MediTrack.Vitaldatenmanagement.application.services;

import com.bht.MediTrack.Vitaldatenmanagement.domain.events.VitaldatenErstelltEvent;
import com.bht.MediTrack.Vitaldatenmanagement.domain.events.VitaldatenUpdateEvent;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import com.bht.MediTrack.Vitaldatenmanagement.infrastructure.repositories.VitaldatenRepository;
import com.bht.MediTrack.PublisherEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.bht.MediTrack.Vitaldatenmanagement.exceptions.InvalidVitaldatenException;
import com.bht.MediTrack.Vitaldatenmanagement.exceptions.VitaldatenNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VitaldatenService {

    private final PublisherEvent eventListener;
    private static final short MIN_HERZFREQUENZ = 0;
    private static final short MAX_HERZFREQUENZ = 220;
    private static final byte MIN_ATEMFREQUENZ = 0;
    private static final byte MAX_ATEMFREQUENZ = 70;
    private static final short MIN_SYSTOLISCH = 0;
    private static final short MAX_SYSTOLISCH = 300;
    private static final short MIN_DIASTOLISCH = 0;
    private static final short MAX_DIASTOLISCH = 170;
    private static final float MIN_TEMPERATUR = 25.0f;
    private static final float MAX_TEMPERATUR = 45.0f;

    @Autowired  // Field injection
    private final VitaldatenRepository vitaldatenRepository;

    public Optional<Vitaldaten> findById(UUID id) {
        return vitaldatenRepository.findById(id);
    }

    @Autowired  // Constructor injection -> das andere oben reicht, ist hier jetzt gedoppelt
    public VitaldatenService(VitaldatenRepository vitaldatenRepository, PublisherEvent eventPublisher) {
        this.vitaldatenRepository = vitaldatenRepository;
        this.eventListener = eventPublisher;
    }

    public Vitaldaten upsertVitaldaten(UUID patientId, final Vitaldaten vitaldaten) {
        if (vitaldaten == null || vitaldaten.getHerzfrequenz() < 30 || vitaldaten.getHerzfrequenz() > 200) {
            throw new InvalidVitaldatenException("Invalid Herzfrequenz value");
        }
        //Optional<Vitaldaten> existingVitaldaten = vitaldatenRepository.findById(vitaldaten.getId());
        //if (existingVitaldaten.isEmpty()) {
        //    throw new VitaldatenNotFoundException("Vitaldaten not found");
        //}
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
        if (savedVitaldaten == null) {
            throw new InvalidVitaldatenException("Failed to save Vitaldaten");
        }

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
/*
    public void deleteVitaldaten(UUID id) {
        Optional<Vitaldaten> vitaldaten = vitaldatenRepository.findById(id);
        if (vitaldaten.isPresent()) {
            vitaldatenRepository.delete(vitaldaten.get());
        } else {
            throw new VitaldatenNotFoundException("Vitaldaten with ID " + id + " not found");
        }
    }
*/
public void deleteVitaldaten(UUID patientId, UUID vitaldatenId) {
    Optional<Vitaldaten> optionalVitaldaten = vitaldatenRepository.findById(vitaldatenId);
    if (optionalVitaldaten.isEmpty() || !optionalVitaldaten.get().getPatient().getId().equals(patientId)) {
        throw new VitaldatenNotFoundException("Vitaldaten with ID " + vitaldatenId + " not found for patient with ID " + patientId);
    }
    vitaldatenRepository.deleteByPatientIdAndId(patientId, vitaldatenId);
}
    /*
    public Vitaldaten deleteVitaldaten(UUID patientId, Vitaldaten vitaldaten) {
        if (patientId == null) {
            throw new InvalidVitaldatenException("PatientId cannot be null");
        }
        if (vitaldaten == null) {
            throw new InvalidVitaldatenException("Vitaldaten is null");
        }

        Optional<Vitaldaten> existingVitaldaten = vitaldatenRepository.delete(vitaldaten.getId());
        if (existingVitaldaten.isEmpty()) {
            throw new VitaldatenNotFoundException(
                    "Vitaldaten with ID " + vitaldaten.getId() + " not found"
            );
        }
        return vitaldatenRepository.delete(patientId, vitaldaten);
    }

     */
    /*
    public Optional<Vitaldaten> getVitaldatenByPatientenId(UUID patientId) {
        return vitaldatenRepository.getVitaldatenByPatientenId(patientId);
    }

    public Optional<Vitaldaten> getVitaldatenById(UUID id) {
        return vitaldatenRepository.getVitaldatenById(id);
    }

    public Vitaldaten updateVitaldaten(UUID patientId, Vitaldaten vitaldaten) {
        if (patientId == null) {
            throw new InvalidVitaldatenException("PatientId cannot be null");
        }
        if (vitaldaten == null) {
            throw new InvalidVitaldatenException("Vitaldaten cannot be null");
        }
        if(!validateVitaldaten(vitaldaten)) {
            throw new InvalidVitaldatenException("Vitaldaten is invalid");
        }
        
        Optional<Vitaldaten> existingVitaldaten = vitaldatenRepository.getVitaldatenById(vitaldaten.getId());
    if (existingVitaldaten.isEmpty()) {
        throw new VitaldatenNotFoundException(
            "Vitaldaten with ID " + vitaldaten.getId() + " not found"
        );
    }
        VitaldatenUpdateEvent event = new VitaldatenUpdateEvent(
                vitaldaten.getId(),
                vitaldaten.getHerzfrequenz(),
                vitaldaten.getAtemfrequenz(),
                vitaldaten.getSystolisch(),
                vitaldaten.getDiastolisch(),
                vitaldaten.getTemperatur(),
                vitaldaten.getDatum()
        );

        eventListener.publishEvent(event);
        return vitaldatenRepository.updateVitaldaten(patientId, vitaldaten);
    }

    public Vitaldaten createVitaldaten(UUID patientId, Vitaldaten vitaldaten) {
        if (patientId == null) {
            throw new InvalidVitaldatenException("PatientId cannot be null");
        }
        if (vitaldaten == null) {
            throw new InvalidVitaldatenException("Vitaldaten is null");    
        }

        if(!validateVitaldaten(vitaldaten)) {
            throw new InvalidVitaldatenException("Vitaldaten is invalid");
        }


        Vitaldaten createdVitaldaten = vitaldatenRepository.createVitaldaten(patientId, vitaldaten);

        VitaldatenErstelltEvent event = new VitaldatenErstelltEvent(
                createdVitaldaten.getId(),
                createdVitaldaten.getHerzfrequenz(),
                createdVitaldaten.getAtemfrequenz(),
                createdVitaldaten.getSystolisch(),
                createdVitaldaten.getDiastolisch(),
                createdVitaldaten.getTemperatur(),
                LocalDateTime.now()
        );

        eventPublisher.publishEvent(event);


        return vitaldatenRepository.createVitaldaten(patientId, vitaldaten);
    }
    public Vitaldaten deleteVitaldaten(UUID patientId, Vitaldaten vitaldaten) {
        if (patientId == null) {
            throw new InvalidVitaldatenException("PatientId cannot be null");
        }
        if (vitaldaten == null) {
            throw new InvalidVitaldatenException("Vitaldaten is null");    
        }

        Optional<Vitaldaten> existingVitaldaten = vitaldatenRepository.getVitaldatenById(vitaldaten.getId());
        if (existingVitaldaten.isEmpty()) {
            throw new VitaldatenNotFoundException(
                "Vitaldaten with ID " + vitaldaten.getId() + " not found"
            );
        }
        return vitaldatenRepository.deleteVitaldaten(patientId, vitaldaten);
    }
    private boolean validateVitaldaten(Vitaldaten vitaldaten) {
        if (vitaldaten.getHerzfrequenz() < MIN_HERZFREQUENZ || vitaldaten.getHerzfrequenz() > MAX_HERZFREQUENZ) {
            throw new InvalidVitaldatenException("Herzfrequenz must be between " + MIN_HERZFREQUENZ + " and " + MAX_HERZFREQUENZ);
        }
        if (vitaldaten.getAtemfrequenz() < MIN_ATEMFREQUENZ || vitaldaten.getAtemfrequenz() > MAX_ATEMFREQUENZ) {
            throw new InvalidVitaldatenException("Atemfrequenz must be between " + MIN_ATEMFREQUENZ + " and " + MAX_ATEMFREQUENZ);
        }
        if (vitaldaten.getSystolisch() < MIN_SYSTOLISCH || vitaldaten.getSystolisch() > MAX_SYSTOLISCH) {
            throw new InvalidVitaldatenException("Systolisch must be between " + MIN_SYSTOLISCH + " and " + MAX_SYSTOLISCH);
        }
        if (vitaldaten.getDiastolisch() < MIN_DIASTOLISCH || vitaldaten.getDiastolisch() > MAX_DIASTOLISCH) {
            throw new InvalidVitaldatenException("Diastolisch must be between " + MIN_DIASTOLISCH + " and " + MAX_DIASTOLISCH);
        }
        if (vitaldaten.getTemperatur() < MIN_TEMPERATUR || vitaldaten.getTemperatur() > MAX_TEMPERATUR) {
            throw new InvalidVitaldatenException("Temperatur must be between " + MIN_TEMPERATUR + " and " + MAX_TEMPERATUR);
        }
        return true;
    }

     */
}
