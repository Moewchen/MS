package com.bht.MediTrack.Repositories;
import com.bht.MediTrack.Entities.Patient;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryPatientRepository {
    private final Map<UUID, Patient> patientStorage = new HashMap<>();

    // Speichert einen neuen Patienten
    public Patient save(Patient patient) {
        if (patient.getId() == null) {
            patient.setId(UUID.randomUUID());
        }
        patientStorage.put(patient.getId(), patient);
        return patient;
    }

    // Einen neuen Patienten anlegen
    public Patient createPatient(Patient patient) {
        patientStorage.put(patient.getId(), patient);
        return patient;
    }


    // Findet einen Patienten anhand der ID
    public Optional<Patient> findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID darf nicht null sein.");
        }
        return Optional.ofNullable(patientStorage.get(id));
    }

    // Gibt alle Patienten zurück
    public List<Patient> findAll() {
        return new ArrayList<>(patientStorage.values());
    }

    // Patient anhand des Namens abrufen
    public List<Patient> getPatientByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht null oder leer sein.");
        }
        return patientStorage.values().stream()
                .filter(patient -> patient.getFirstName().equalsIgnoreCase(name) ||
                        patient.getLastName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    // Patient anhand des Geburtsdatums abrufen
    public List<Patient> getPatientByGeburtsdatum(LocalDate geburtsdatum) {
        if (geburtsdatum == null) {
            throw new IllegalArgumentException("Datum darf nicht null sein.");
        }
        return patientStorage.values().stream()
                .filter(patient -> patient.getDateOfBirth().equals(geburtsdatum))
                .collect(Collectors.toList());
    }

    // Findet Patienten anhand der Krankenversicherungsnummer
    public Optional<Patient> findByKrankenversicherungsnummer(String krankenversicherungsnummer) {
        return patientStorage.values().stream()
                .filter(patient -> patient.getKrankenversicherungsnummer().equals(krankenversicherungsnummer))
                .findFirst();
    }

    // Löscht einen Patienten anhand der ID
    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID darf nicht null sein.");
        }
        patientStorage.remove(id);
    }

    // Löscht alle Patienten
    public void deleteAll() {
        patientStorage.clear();
    }

    // Findet Patienten nach Krankenkasse
    public List<Patient> findByKrankenkasse(String krankenkasse) {
        return patientStorage.values().stream()
                .filter(patient -> patient.getKrankenkasse().equalsIgnoreCase(krankenkasse))
                .collect(Collectors.toList());
    }

    // Patientendaten aktualisieren
    public Optional<Patient> updatePatient(UUID id, Patient updatedPatient) {
        return Optional.ofNullable(patientStorage.computeIfPresent(id, (k, v) -> updatedPatient));
    }

}
