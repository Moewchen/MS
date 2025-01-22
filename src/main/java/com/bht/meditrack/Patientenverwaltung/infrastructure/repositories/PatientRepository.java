package com.bht.meditrack.Patientenverwaltung.infrastructure.repositories;
import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.meditrack.Patientenverwaltung.infrastructure.persistence.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PatientRepository extends JpaRepository<PatientEntity,UUID> {
    List<PatientEntity> findByKrankenkasse(Krankenkasse krankenkasse);
    Optional<PatientEntity> findById(UUID id);
    void deleteById(UUID id);
}
