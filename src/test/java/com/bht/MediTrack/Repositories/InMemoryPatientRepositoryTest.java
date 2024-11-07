package com.bht.MediTrack.Repositories;
import com.bht.MediTrack.Entities.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryPatientRepositoryTest {

    private InMemoryPatientRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryPatientRepository();
    }

    @Test
    public void shouldSaveAndFindPatientById() {
        Patient patient = new Patient("John", "Doe", "Mr", LocalDate.of(1990, 1, 1),
                "123456", "john@example.com", "Address 1",
                "AOK", "K12345");
        Patient savedPatient = repository.save(patient);
        Optional<Patient> foundPatient = repository.findById(savedPatient.getId());

        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get().getFirstName()).isEqualTo("John");
    }

    @Test
    public void shouldRetrievePatientByName() {
        Patient patient = new Patient("Bob", "Johnson", "Mr", LocalDate.of(1985, 5, 15),
                "654321", "bob@example.com", "Address 2", "BKK", "K67890");
        repository.createPatient(patient);

        var foundPatients = repository.getPatientByName("Bob");

        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getLastName()).isEqualTo("Johnson");
    }

    @Test
    public void shouldRetrievePatientByGeburtsdatum() {
        Patient patient = new Patient("Charlie", "Brown", "Dr", LocalDate.of(1975, 3, 25),
                "789789", "charlie@example.com", "Address 3", "TK", "K54321");
        repository.createPatient(patient);

        var foundPatients = repository.getPatientByGeburtsdatum(LocalDate.of(1975, 3, 25));

        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getFirstName()).isEqualTo("Charlie");
    }

    @Test
    public void shouldFindPatientByKrankenversicherungsnummer() {
        Patient patient = new Patient("Jane", "Doe", "Ms", LocalDate.of(1985, 5, 15),
                "654321", "jane@example.com", "Address 2",
                "BKK", "K67890");
        repository.save(patient);

        Optional<Patient> foundPatient = repository.findByKrankenversicherungsnummer("K67890");

        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get().getFirstName()).isEqualTo("Jane");
    }

    @Test
    public void shouldFindAllPatients() {
        Patient patient1 = new Patient("John", "Doe", "Mr", LocalDate.of(1990, 1, 1),
                "123456", "john@example.com", "Address 1",
                "AOK", "K12345");
        Patient patient2 = new Patient("Jane", "Doe", "Ms", LocalDate.of(1985, 5, 15),
                "654321", "jane@example.com", "Address 2",
                "BKK", "K67890");
        repository.save(patient1);
        repository.save(patient2);

        List<Patient> allPatients = repository.findAll();

        assertThat(allPatients).hasSize(2);
    }

    @Test
    public void shouldDeletePatientById() {
        Patient patient = new Patient("John", "Doe", "Mr", LocalDate.of(1990, 1, 1),
                "123456", "john@example.com", "Address 1",
                "AOK", "K12345");
        Patient savedPatient = repository.save(patient);
        repository.deleteById(savedPatient.getId());

        assertThat(repository.findById(savedPatient.getId())).isNotPresent();
    }

    @Test
    public void shouldUpdatePatient() {
        Patient patient = new Patient("Eve", "Lewis", "Mrs", LocalDate.of(1992, 12, 12),
                "654654", "eve@example.com", "Address 5", "AOK", "K98765");
        repository.createPatient(patient);

        patient.setEmail("eve_updated@example.com");
        var updatedPatient = repository.updatePatient(patient.getId(), patient);

        assertThat(updatedPatient).isPresent();
        assertThat(updatedPatient.get().getEmail()).isEqualTo("eve_updated@example.com");
    }
}
