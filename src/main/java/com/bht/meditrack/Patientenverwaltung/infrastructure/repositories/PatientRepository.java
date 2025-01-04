package com.bht.meditrack.Patientenverwaltung.infrastructure.repositories;
import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PatientRepository extends JpaRepository<Patient,UUID> {
}
