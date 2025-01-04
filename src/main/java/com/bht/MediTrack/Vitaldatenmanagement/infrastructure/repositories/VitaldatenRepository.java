package com.bht.MediTrack.Vitaldatenmanagement.infrastructure.repositories;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;


public interface VitaldatenRepository extends JpaRepository<Vitaldaten,UUID> {
    List<Vitaldaten> findByPatientId(UUID patientId);
    void deleteByPatientIdAndId(UUID patientId, UUID id);
}
