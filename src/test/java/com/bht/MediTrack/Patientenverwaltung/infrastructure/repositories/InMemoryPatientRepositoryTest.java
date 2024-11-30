package com.bht.MediTrack.Patientenverwaltung.infrastructure.repositories;

import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class InMemoryPatientRepositoryTest {

    private InMemoryPatientRepository repository;
    @BeforeEach
    public void setUp() {
        repository = new InMemoryPatientRepository();
    }
    @Test
    public void shouldSaveAndFindPatientById() {
        Patient patient = new Patient(
                new UUID(2,3),
                new Krankenkasse("AOK"),
                "123456",
                new Personendaten("John", "Doe", "Mr", LocalDate.of(1990, 1, 1)),
                new Kontaktdaten("john@example.com","+491234567890"),
                new Adresse("Musterstraße","1","11111","test")
        );
        Patient savedPatient = repository.save(patient);
        Optional<Patient> foundPatient = repository.findById(savedPatient.getId());
        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get().getPersonendaten().firstName()).isEqualTo("John");
    }
    @Test
    public void shouldRetrievePatientByName() {
        Patient patient = new Patient(
                new UUID(2,3),
                new Krankenkasse("BKK"),
                "K67890",
                new Personendaten("Bob", "Johnson", "Mr", LocalDate.of(1985, 5, 15)),
                new Kontaktdaten("bob@example.com","+491234567890"),
                new Adresse("Musterstraße","1","11111","test")
        );

        repository.createPatient(patient);
        var foundPatients = repository.getPatientByName("Bob");
        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getPersonendaten().lastName()).isEqualTo("Johnson");
    }
    @Test
    public void shouldRetrievePatientByGeburtsdatum() {
        Patient patient = new Patient(
                new UUID(2,3),
                new Krankenkasse("TK"),
                "K54321",
                new Personendaten("Charlie", "Brown", "Dr", LocalDate.of(1975, 3, 25)),
                new Kontaktdaten("charlie@example.com","+491234567890"),
                new Adresse("Adresse","3","11111","test")
        );
        repository.createPatient(patient);
        var foundPatients = repository.getPatientByGeburtsdatum(LocalDate.of(1975, 3, 25));
        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getPersonendaten().firstName()).isEqualTo("Charlie");
    }
    @Test
    public void shouldFindPatientByKrankenversicherungsnummer() {
        Patient patient = new Patient(
                new UUID(2,3),
                new Krankenkasse("TK"),
                "K67890",
                new Personendaten("Jane", "Brown", "Dr", LocalDate.of(1975, 3, 25)),
                new Kontaktdaten("charlie@example.com","+491234567890"),
                new Adresse("Adresse","3","11111","test")
        );
        repository.save(patient);
        Optional<Patient> foundPatient = repository.findByKrankenversicherungsnummer("K67890");
        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get().getPersonendaten().firstName()).isEqualTo("Jane");
    }
    @Test
    public void shouldFindAllPatients() {
        Patient patient1 = new Patient(
                new UUID(2,3),
                new Krankenkasse("AOK"),
                "K12345",
                new Personendaten("John", "Doe", "Mr", LocalDate.of(1990, 1, 1)),
                new Kontaktdaten("john@example.com","+491234567890"),
                new Adresse("Adresse","3","11111","test")
        );
        Patient patient2 = new Patient(
                new UUID(2,3),
                new Krankenkasse("AOK"),
                "K76765",
                new Personendaten("Jane", "Doe", "Ms", LocalDate.of(1985, 5, 15)),
                new Kontaktdaten("jane@example.com","+491234567890"),
                new Adresse("Adresse","3","11111","test")
        );
        repository.save(patient1);
        repository.save(patient2);
        List<Patient> allPatients = repository.findAll();
        assertThat(allPatients).hasSize(2);
    }
    @Test
    public void shouldDeletePatientById() {
        Patient patient = new Patient(
                new UUID(2,3),
                new Krankenkasse("AOK"),
                "123456",
                new Personendaten("John", "Doe", "Mr", LocalDate.of(1990, 1, 1)),
                new Kontaktdaten("john@example.com","+491234567890"),
                new Adresse("Musterstraße","1","11111","test")
        );
        Patient savedPatient = repository.save(patient);
        repository.deleteById(savedPatient.getId());
        assertThat(repository.findById(savedPatient.getId())).isNotPresent();
    }
    @Test
    public void shouldUpdatePatient() {
        Patient patient = new Patient(
                new UUID(2,3),
                new Krankenkasse("AOK"),
                "123456",
                new Personendaten("Eve", "Lewis", "Mrs", LocalDate.of(1992, 12, 12)),
                new Kontaktdaten("john@example.com","+491234567890"),
                new Adresse("Musterstraße","1","11111","test")
        );
        repository.createPatient(patient);
        patient.setKontaktdaten(new Kontaktdaten("eve_updated@example.com","+491234567890"));
        var updatedPatient = repository.updatePatient(patient.getId(), patient);
        assertThat(updatedPatient).isPresent();
        assertThat(updatedPatient.get().getKontaktdaten().email()).isEqualTo("eve_updated@example.com");
    }
}