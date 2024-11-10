package com.bht.MediTrack.Services;

import com.bht.MediTrack.Entities.Vitaldaten;
import com.bht.MediTrack.Repositories.VitaldatenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bht.MediTrack.Exceptions.InvalidVitaldatenException;
import com.bht.MediTrack.Exceptions.VitaldatenNotFoundException;

import java.util.Optional;
import java.util.UUID;

@Service
public class VitaldatenService {

    private final VitaldatenRepository vitaldatenRepository;
    private static final short MIN_HERZFREQUENZ = 0;
    private static final short MAX_HERZFREQUENZ = 220;

    @Autowired
    public VitaldatenService(VitaldatenRepository vitaldatenRepository) {
        this.vitaldatenRepository = vitaldatenRepository;
    }

    public Optional<Vitaldaten> getVitaldatenByPatientenId(UUID patientId) {
        return vitaldatenRepository.getVitaldatenByPatientenId(patientId);
    }

    public Optional<Vitaldaten> getVitaldatenById(UUID id) {
        return vitaldatenRepository.getVitaldatenById(id);
    }

    public boolean updateVitaldaten(UUID patientId, Vitaldaten vitaldaten) {
        Optional<Vitaldaten> existingVitaldaten = vitaldatenRepository.getVitaldatenById(vitaldaten.getId());
    if (existingVitaldaten.isEmpty()) {
        throw new VitaldatenNotFoundException(
            "Vitaldaten with ID " + vitaldaten.getId() + " not found"
        );
    }
        return vitaldatenRepository.updateVitaldaten(patientId, vitaldaten);
    }

    public Vitaldaten createVitaldaten(UUID patientId, Vitaldaten vitaldaten) {
        if (vitaldaten == null) {
            throw new InvalidVitaldatenException("Vitaldaten is null");
            
        }
        if (vitaldaten.getHerzfrequenz() < MIN_HERZFREQUENZ || 
        vitaldaten.getHerzfrequenz() > MAX_HERZFREQUENZ) {
        throw new InvalidVitaldatenException(
            "Herzfrequenz must be between " + MIN_HERZFREQUENZ + 
            " and " + MAX_HERZFREQUENZ
        );
    }
        return vitaldatenRepository.createVitaldaten(patientId, vitaldaten);
    }
    public Vitaldaten deleteVitaldaten(UUID patientId, Vitaldaten vitaldaten) {
                Optional<Vitaldaten> existingVitaldaten = vitaldatenRepository.getVitaldatenById(vitaldaten.getId());
        if (existingVitaldaten.isEmpty()) {
            throw new VitaldatenNotFoundException(
                "Vitaldaten with ID " + vitaldaten.getId() + " not found"
            );
        }
        return vitaldatenRepository.deleteVitaldaten(patientId, vitaldaten);
    }
}
