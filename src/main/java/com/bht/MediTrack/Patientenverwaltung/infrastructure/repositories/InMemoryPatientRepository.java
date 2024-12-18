package com.bht.MediTrack.Patientenverwaltung.infrastructure.repositories;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/*
public interface InMemoryPatientRepository extends CrudRepository<Patient, UUID> {
}

 */

public interface InMemoryPatientRepository extends JpaRepository<Patient,UUID> {
}
/*
@Repository
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
                .filter(patient -> patient.getPersonendaten().firstName().equalsIgnoreCase(name) ||
                        patient.getPersonendaten().lastName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    // Patient anhand des Geburtsdatums abrufen
    public List<Patient> getPatientByGeburtsdatum(LocalDate geburtsdatum) {
        if (geburtsdatum == null) {
            throw new IllegalArgumentException("Datum darf nicht null sein.");
        }
        return patientStorage.values().stream()
                .filter(patient -> patient.getPersonendaten().dateOfBirth().equals(geburtsdatum))
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
                .filter(patient -> patient.getKrankenkasse().krankenkasse().equalsIgnoreCase(krankenkasse))
                .collect(Collectors.toList());
    }

    // Patientendaten aktualisieren
    public Optional<Patient> updatePatient(UUID id, Patient updatedPatient) {
        return Optional.ofNullable(patientStorage.computeIfPresent(id, (k, v) -> updatedPatient));
    }

}
*/