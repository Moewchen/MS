package com.bht.MediTrack.Repositories;
import com.bht.MediTrack.Entities.Patient;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.Repository;


public interface PatientRepository extends Repository<Patient, Long> {
    List<Patient> findPatientByName(String firstname, String lastname);
    List<Patient> findPatientByGeburtsdatum(Date geburtsdatum);
    Patient create(Patient patient);
    Patient update(Patient patient);
    void deleteById(UUID id);
    Optional<Patient> findById(UUID id);
}
