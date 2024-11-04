package com.bht.MediTrack.Services;

import com.bht.MediTrack.Entities.Vitaldaten;
import com.bht.MediTrack.Repositories.VitaldatenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VitaldatenService {

    private final VitaldatenRepository vitaldatenRepository;

    @Autowired
    public VitaldatenService(VitaldatenRepository vitaldatenRepository) {
        this.vitaldatenRepository = vitaldatenRepository;
    }

    public Optional<Vitaldaten> getVitaldatenByPatientenId(UUID patientId) {
        return vitaldatenRepository.getVitaldatenByPatientenId(patientId);
    }

    public Optional<Vitaldaten> getVitaldatenById(UUID id) {
        return vitaldatenRepository.getVitaldatenByID(id);
    }

    public boolean updateVitaldaten(UUID patientId, Vitaldaten vitaldaten) {
        return vitaldatenRepository.updateVitaldaten(patientId, vitaldaten);
    }

    public Vitaldaten createVitaldaten(UUID patientId, Vitaldaten vitaldaten) {
        return vitaldatenRepository.createVitaldaten(patientId, vitaldaten);
    }
}