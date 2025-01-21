package com.bht.meditrack.Patientenverwaltung.infrastructure.repositories;
import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    List<Patient> findByKrankenkasse(Krankenkasse krankenkasse);
    Optional<Patient> findById(UUID id);
    void deleteById(UUID id);
}
