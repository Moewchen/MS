package com.bht.meditrack.patientenverwaltung.infrastructure.repositories;
import com.bht.meditrack.patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.meditrack.patientenverwaltung.infrastructure.persistence.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PatientRepository extends JpaRepository<PatientEntity,UUID> {
    List<PatientEntity> findByKrankenkasse(Krankenkasse krankenkasse);
    Optional<PatientEntity> findById(UUID id);
    void deleteById(UUID id);
}
