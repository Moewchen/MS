package com.bht.MediTrack.Patientenverwaltung.infrastructure.repositories;

import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PatientRepositoryTest {

    @Autowired
    private PatientRepository repository;

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient(
                UUID.randomUUID(),
                new Krankenkasse("AOK"),
                "123456789012",
                new Personendaten("John", "Doe", "Mr", LocalDate.of(1990, 1, 1)),
                new Kontaktdaten("john@example.com", "01234567890"),
                new Adresse("Musterstraße", "1", "11111", "Musterstadt")
        );
    }

    @Test
    void shouldSaveAndFindPatientById() {
        Patient savedPatient = repository.save(patient);
        Optional<Patient> foundPatient = repository.findById(savedPatient.getId());
        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get().getPersonendaten().firstName()).isEqualTo("John");
    }

    @Test
    void shouldRetrievePatientByName() {
        repository.save(patient);
        var foundPatients = repository.findAll().stream()
                .filter(p -> p.getPersonendaten().firstName().equals("John"))
                .toList();
        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getPersonendaten().lastName()).isEqualTo("Doe");
    }

    @Test
    void shouldRetrievePatientByGeburtsdatum() {
        repository.save(patient);
        var foundPatients = repository.findAll().stream()
                .filter(p -> p.getPersonendaten().dateOfBirth().equals(LocalDate.of(1990, 1, 1)))
                .toList();
        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getPersonendaten().firstName()).isEqualTo("John");
    }

    @Test
    void shouldFindPatientByKrankenversicherungsnummer() {
        repository.save(patient);
        var foundPatient = repository.findAll().stream()
                .filter(p -> p.getKrankenversicherungsnummer().equals("123456789012"))
                .findFirst();
        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get().getPersonendaten().firstName()).isEqualTo("John");
    }

    @Test
    void shouldFindAllPatients() {
        repository.save(patient);
        var patients = repository.findAll();
        assertThat(patients).isNotEmpty();
    }

    @Test
    void shouldDeletePatientById() {
        Patient savedPatient = repository.save(patient);
        repository.deleteById(savedPatient.getId());
        Optional<Patient> foundPatient = repository.findById(savedPatient.getId());
        assertThat(foundPatient).isNotPresent();
    }

    @Test
    void shouldUpdatePatient() {
        Patient savedPatient = repository.save(patient);
        savedPatient.setKontaktdaten(new Kontaktdaten("john_updated@example.com", "+491234567891"));
        Patient updatedPatient = repository.save(savedPatient);
        assertThat(updatedPatient.getKontaktdaten().email()).isEqualTo("john_updated@example.com");
    }
}
