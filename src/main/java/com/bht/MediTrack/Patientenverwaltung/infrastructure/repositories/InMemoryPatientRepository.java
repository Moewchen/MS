package com.bht.MediTrack.Patientenverwaltung.infrastructure.repositories;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface InMemoryPatientRepository extends JpaRepository<Patient,UUID> {
}
