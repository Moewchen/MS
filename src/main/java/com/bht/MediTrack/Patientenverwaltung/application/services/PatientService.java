package com.bht.MediTrack.Patientenverwaltung.application.services;
import com.bht.MediTrack.Patientenverwaltung.domain.events.PatientAngelegtEvent;
import com.bht.MediTrack.Patientenverwaltung.domain.events.PatientEntferntEvent;
import com.bht.MediTrack.Patientenverwaltung.domain.events.PatientUpdateEvent;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.MediTrack.Patientenverwaltung.infrastructure.repositories.PatientRepository;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PatientService {


    @Autowired // Field injection
    private final PatientRepository patientRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired // Constructor injection
    public PatientService(PatientRepository patientRepository, ApplicationEventPublisher eventPublisher) {
        this.patientRepository = Objects.requireNonNull(patientRepository, "Repository darf nicht null sein.");
        this.eventPublisher = eventPublisher;
    }


    public Patient upsertPatient(UUID patientId, final Patient patient) {
        if (patientId == null) {
            throw new IllegalArgumentException("PatientId darf nicht null sein.");
        }
        if (patient == null) {
            throw new IllegalArgumentException("Patient darf nicht null sein.");
        }
        patient.setId(patientId);
        Patient savedPatient = patientRepository.save(patient);
        eventPublisher.publishEvent(new PatientUpdateEvent(patientId, "Patient", patient.toString()));
        return savedPatient;
    }

    public Patient createPatient(Patient patient) {
        if (patient.getPersonendaten().firstName() == null || patient.getPersonendaten().lastName() == null || patient.getPersonendaten().dateOfBirth() == null || patient.getKontaktdaten().email() == null) {
            throw new IllegalArgumentException("Pflichtfelder dürfen nicht null sein.");
        }
        UUID patientId = patient.getId() != null ? patient.getId() : UUID.randomUUID();
        patient.setId(patientId);
        PatientAngelegtEvent event = new PatientAngelegtEvent(patient.getId(), patient.getKrankenkasse(), patient.getKrankenversicherungsnummer(), patient.getPersonendaten(), patient.getKontaktdaten(), patient.getAdresse());
        eventPublisher.publishEvent(event);
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return (List<Patient>) patientRepository.findAll();
    }

    public Patient findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID darf nicht null sein.");
        }
        return patientRepository.findById(id).orElse(null);
    }

//    public Optional<Patient> findPatientById(UUID id) {
//        if (id == null) {
//            throw new IllegalArgumentException("ID darf nicht null sein.");
//        }
//        return patientRepository.findById(id);
//    }



    public void deletePatient(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID darf nicht null sein.");
        }
        patientRepository.deleteById(id);
        eventPublisher.publishEvent(new PatientEntferntEvent(id));
    }

    public void deleteAllPatients() {
        patientRepository.deleteAll();
    }

    public Optional<Patient> updatePatient(UUID id, Patient updatedPatient) {
        if (id == null) {
            throw new IllegalArgumentException("ID darf nicht null sein.");
        }
        if (updatedPatient == null) {
            throw new IllegalArgumentException("UpdatedPatient darf nicht null sein.");
        }
        return patientRepository.findById(id)
                .map(existingPatient -> {
                    updatedPatient.setId(existingPatient.getId());
                    Patient savedPatient = patientRepository.save(updatedPatient);
                    eventPublisher.publishEvent(new PatientUpdateEvent(id, "Patient", updatedPatient.toString()));
                    return savedPatient;
                });
    }


    /*
    private final InMemoryPatientRepository patientRepository;
    private final ApplicationEventPublisher eventPublisher;
*/
    /*
    public PatientService(InMemoryPatientRepository patientRepository, ApplicationEventPublisher eventPublisher) {
        this.patientRepository = Objects.requireNonNull(patientRepository, "Repository darf nicht null sein.");
        this.eventPublisher = eventPublisher;
    }
    */

/*
    // Erstellt und speichert einen neuen Patienten
    public Patient createPatient(Krankenkasse krankenkasse, String krankenversicherungsnummer, Personendaten personendaten, Kontaktdaten kontaktdaten, Adresse adresse) {
        if (personendaten.firstName() == null || personendaten.lastName() == null || personendaten.dateOfBirth() == null || kontaktdaten.email() == null) {
            throw new IllegalArgumentException("Pflichtfelder dürfen nicht null sein.");
        }
        UUID patientId = UUID.randomUUID();
        Patient patient = new Patient(patientId, krankenkasse, krankenversicherungsnummer, personendaten, kontaktdaten, adresse);
        //Patient createdPatient = patientRepository.createPatient(patient);
        // Event auslösen
        PatientAngelegtEvent event = new PatientAngelegtEvent(patient.getId(), patient.getKrankenkasse(), patient.getKrankenversicherungsnummer(), patient.getPersonendaten(), patient.getKontaktdaten(), patient.getAdresse());
        eventPublisher.publishEvent(event);

                return patientRepository.save(patient);
    }

 */

    /*
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

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

     */
}
