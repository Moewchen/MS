package com.bht.meditrack.Vitaldatenmanagement.infrastructure.repositories;
import com.bht.meditrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import com.bht.meditrack.Vitaldatenmanagement.infrastructure.persistence.VitaldatenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;


public interface VitaldatenRepository extends JpaRepository<VitaldatenEntity,UUID> {
    List<VitaldatenEntity> findByPatientId(UUID patientId);
    void deleteByPatientIdAndId(UUID patientId, UUID id);
}
