package com.bht.MediTrack.Services;
import com.bht.MediTrack.Entities.Patient;
import com.bht.MediTrack.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PatientverwaltungService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        return patientRepository.create(patient);
    }

    public Patient getPatientById(UUID id) {
        return patientRepository.findById(id).orElse(null);
    }

    public List<Patient> getPatientByName(String firstName, String lastname) {
        return patientRepository.findPatientByName(firstName, lastname);
    }

    public List<Patient> getPatientByGeburtsdatum(Date geburtsdatum) {
        return patientRepository.findPatientByGeburtsdatum(geburtsdatum);
    }

    public Patient updatePatient(UUID id, Patient patientDetails) {
        return patientRepository.findById(id).map(patient -> {
            patient.setFirstName(patientDetails.getFirstName());
            patient.setLastName(patientDetails.getLastName());
            patient.setKrankenkasse(patientDetails.getKrankenkasse());
            return patientRepository.create(patient);
        }).orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
