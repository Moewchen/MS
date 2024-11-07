package com.bht.MediTrack.Services;
import com.bht.MediTrack.Entities.Patient;
import com.bht.MediTrack.Repositories.InMemoryPatientRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PatientService {

    private final InMemoryPatientRepository patientRepository;

    public PatientService(InMemoryPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Erstellt und speichert einen neuen Patienten
    public Patient createPatient(String firstName, String lastName, String titel, LocalDate dateOfBirth,
            String telefon, String email, String adresse, String krankenkasse,
            String krankenversicherungsnummer) {
        Patient patient = new Patient(firstName, lastName, titel, dateOfBirth, telefon, email, adresse,
                krankenkasse, krankenversicherungsnummer);
        return patientRepository.save(patient);
    }

    // Findet einen Patienten anhand der ID
    public Optional<Patient> findPatientById(UUID id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getPatientByName(String name) {
        return patientRepository.getPatientByName(name);
    }

    public List<Patient> getPatientByGeburtsdatum(LocalDate geburtsdatum) {
        return patientRepository.getPatientByGeburtsdatum(geburtsdatum);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.createPatient(patient);
    }

    // Findet Patienten anhand der Krankenkasse
    public List<Patient> findPatientsByKrankenkasse(String krankenkasse) {
        return patientRepository.findByKrankenkasse(krankenkasse);
    }

    // Findet Patienten, die vor einem bestimmten Jahr geboren wurden
    public List<Patient> findPatientsBornBefore(int year) {
        return patientRepository.findAll().stream()
                .filter(patient -> patient.getDateOfBirth().getYear() < year)
                .collect(Collectors.toList());
    }

    // Findet einen Patienten anhand der Krankenversicherungsnummer
    public Optional<Patient> findPatientByKrankenversicherungsnummer(String krankenversicherungsnummer) {
        return patientRepository.findByKrankenversicherungsnummer(krankenversicherungsnummer);
    }

    // Prüft, ob eine Krankenversicherungsnummer existiert
    public boolean krankenversicherungsnummerExists(String krankenversicherungsnummer) {
        return patientRepository.findByKrankenversicherungsnummer(krankenversicherungsnummer).isPresent();
    }

    // Löscht einen Patienten anhand der ID
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }

    // Löscht alle Patienten
    public void deleteAllPatients() {
        patientRepository.deleteAll();
    }

    public Optional<Patient> updatePatient(UUID id, Patient updatedPatient) {
        return patientRepository.updatePatient(id, updatedPatient);
    }
}
