package com.bht.meditrack.Patientenverwaltung.infrastructure.repositories;
import com.bht.meditrack.Patientenverwaltung.infrastructure.persistence.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PatientRepository extends JpaRepository<PatientEntity,UUID> {
}
