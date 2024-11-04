package com.bht.MediTrack.Services;
import com.bht.MediTrack.Entities.Patient;
import com.bht.MediTrack.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getPatientByName(String name) {
        return patientRepository.findPatientByName(name);
    }

    public List<Patient> getPatientByGeburtsdatum(Date geburtsdatum) {
        return patientRepository.findPatientByGeburtsdatum(geburtsdatum);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        return patientRepository.findById(id).map(patient -> {
            patient.setFirstName(patientDetails.getFirstName());
            patient.setAddress(patientDetails.getAddress());
            patient.setFirstName(patient.getFirstName());
            return patientRepository.save(patient);
        }).orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}




