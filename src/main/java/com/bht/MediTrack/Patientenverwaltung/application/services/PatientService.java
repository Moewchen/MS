package com.bht.MediTrack.Patientenverwaltung.application.services;
import com.bht.MediTrack.Patientenverwaltung.domain.events.PatientAngelegtEvent;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.MediTrack.Patientenverwaltung.infrastructure.repositories.InMemoryPatientRepository;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final InMemoryPatientRepository patientRepository;
    private final ApplicationEventPublisher eventPublisher;

    public PatientService(InMemoryPatientRepository patientRepository, ApplicationEventPublisher eventPublisher) {
        this.patientRepository = Objects.requireNonNull(patientRepository, "Repository darf nicht null sein.");
        this.eventPublisher = eventPublisher;
    }

    // Erstellt und speichert einen neuen Patienten
    public Patient createPatient(String firstName, String lastName, String titel, LocalDate dateOfBirth,
            String telefon, String email, String strasse, String hausnummer, String plz, String ort, String krankenkasse,
            String krankenversicherungsnummer) {
        if (firstName == null || lastName == null || dateOfBirth == null || email == null) {
            throw new IllegalArgumentException("Pflichtfelder dürfen nicht null sein.");
        }
        Patient patient = new Patient(
                new Krankenkasse(krankenkasse),
                krankenversicherungsnummer,
                new Personendaten(firstName,lastName,titel, dateOfBirth),
                new Kontaktdaten(email, telefon),
                new Adresse(strasse, hausnummer, plz, ort));

        //Patient createdPatient = patientRepository.createPatient(patient);
        // Event auslösen
        PatientAngelegtEvent event = new PatientAngelegtEvent(patient.getId(), patient.getKrankenkasse(), patient.getKrankenversicherungsnummer(), patient.getPersonendaten(), patient.getKontaktdaten(), patient.getAdresse());
        eventPublisher.publishEvent(event);

                return patientRepository.save(patient);
    }

    // Findet einen Patienten anhand der ID
    public Optional<Patient> findPatientById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID darf nicht null sein.");
        }
        return patientRepository.findById(id);
    }

    public List<Patient> getPatientByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht null oder leer sein.");
        }
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
        String yearString = String.valueOf(year);

        // Regex für ein gültiges Jahr zwischen 1000 und 2999
        String yearRegex = "^(1[0-9]{3}|2[0-9]{3})$";
        Pattern pattern = Pattern.compile(yearRegex);

        if (!pattern.matcher(yearString).matches()) {
            throw new IllegalArgumentException("Ungültiges Jahr: " + year);
        }

        return patientRepository.findAll().stream()
                .filter(patient -> patient.getPersonendaten().dateOfBirth() != null && patient.getPersonendaten().dateOfBirth().getYear() < year)
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
        if (id == null) {
            throw new IllegalArgumentException("ID darf nicht null sein.");
        }
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
