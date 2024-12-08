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
import java.util.Optional;
import java.util.UUID;

@Service
public class VitaldatenService {

    private final VitaldatenRepository vitaldatenRepository;
    private final ApplicationEventPublisher eventPublisher;
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

    @Autowired
    public VitaldatenService(VitaldatenRepository vitaldatenRepository, ApplicationEventPublisher eventPublisher, PublisherEvent eventListener) {
        this.vitaldatenRepository = vitaldatenRepository;
        this.eventPublisher = eventPublisher;
        this.eventListener = eventListener;
    }

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
}
