package com.bht.MediTrack.Patientenverwaltung.application.services;

import java.util.UUID;

import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.MediTrack.Patientenverwaltung.infrastructure.repositories.InMemoryPatientRepository;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

class PatientServiceTest {

    /*
    private PatientService patientService;
    private InMemoryPatientRepository repository;
    private ApplicationEventPublisher eventPublisher;
    @BeforeEach
    public void setUp() {
        eventPublisher = new ApplicationEventPublisher() {
            @Override
            public void publishEvent(Object event) {}
        };
        repository = new InMemoryPatientRepository();
        patientService = new PatientService(repository,eventPublisher);
    }
    @Test
    public void testCreatePatient_NullName_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    patientService.createPatient(
                            new Krankenkasse("AOK"), "123456789012",
                            new Personendaten(null, "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                            new Kontaktdaten("max.mustermann@example.com", "+491234567890"),
                            new Adresse("Musterstraße", "1", "12345", "Musterstadt")
                    );
        });
        assertEquals("Vorname darf nicht leer sein", exception.getMessage());
    }
    @Test
    public void shouldCreateAndFindPatientById() {
        Patient patient = patientService.createPatient(
                new Krankenkasse("AOK"), "123456789012",
                new Personendaten("Max", "Miller", "Dr.", LocalDate.of(1985, 5, 20)),
                new Kontaktdaten("max.mustermann@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        Optional<Patient> foundPatient = patientService.findPatientById(patient.getId());
        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get().getPersonendaten().lastName()).isEqualTo("Miller");
    }
    @Test
    public void testFindPatientByIdInvalidIdShouldReturnEmpty() {
        Optional<Patient> foundPatient = patientService.findPatientById(UUID.randomUUID());
        assertFalse(foundPatient.isPresent());
    }
    @Test
    public void testFindPatientByName_EmptyName_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            patientService.getPatientByName("");
        });
        assertEquals("Name darf nicht null oder leer sein.", exception.getMessage());
    }
    @Test
    public void shouldRetrievePatientByName() {
        Patient patient = new Patient(null,
                new Krankenkasse("AOK"), "123456789012",
                new Personendaten("Bob", "Anderson", "Dr.", LocalDate.of(1985, 5, 20)),
                new Kontaktdaten("max.mustermann@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        patientService.createPatient(patient);
        var foundPatients = patientService.getPatientByName("Bob");
        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getPersonendaten().lastName()).isEqualTo("Anderson");
    }
    @Test
    public void shouldRetrievePatientByGeburtsdatum() {
        Patient patient = new Patient(null,
                new Krankenkasse("AOK"), "123456789012",
                new Personendaten("Charlie", "Anderson", "Dr.", LocalDate.of(1978, 10, 10)),
                new Kontaktdaten("max.mustermann@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        patientService.createPatient(patient);
        var foundPatients = patientService.getPatientByGeburtsdatum(LocalDate.of(1978, 10, 10));
        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getPersonendaten().firstName()).isEqualTo("Charlie");
    }
    @Test
    public void shouldFindPatientsByKrankenkasse() {
        patientService.createPatient(
                new Krankenkasse("TK"), "K54321",
                new Personendaten("Alice", "Miller", "Dr", LocalDate.of(1975, 3, 25)),
                new Kontaktdaten("alice@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        patientService.createPatient(
                new Krankenkasse("AOK"), "K67891",
                new Personendaten("Bob", "Smith", "Mr", LocalDate.of(1982, 7, 20)),
                new Kontaktdaten("bob@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        List<Patient> tkPatients = patientService.findPatientsByKrankenkasse("TK");
        assertThat(tkPatients).hasSize(1);
        assertThat(tkPatients.get(0).getPersonendaten().firstName()).isEqualTo("Alice");
    }
    @Test
    public void testDeletePatientNotExistsIdExceptionThrown() {
        UUID nonexistentId = UUID.randomUUID();
        System.out.println(nonexistentId);
        patientService.deletePatient(nonexistentId);
        assertTrue(repository.findAll().isEmpty());
    }
    @Test
    public void testDeletePatient_ValidId_PatientDeleted() {
        Patient patient = patientService.createPatient(
                new Krankenkasse("DAK"), "K54321",
                new Personendaten("Emily", "Clark", "Dr.", LocalDate.of(2000, 12, 20)),
                new Kontaktdaten("emily@example.com", "+4912345670"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        patientService.deletePatient(patient.getId());
        Optional<Patient> deletedPatient = patientService.findPatientById(patient.getId());
        assertFalse(deletedPatient.isPresent());
    }
    @Test
    public void shouldFindPatientsBornBefore() {
        patientService.createPatient(
                new Krankenkasse("TK"), "K54321",
                new Personendaten("Alice", "Miller", "Dr", LocalDate.of(1975, 3, 25)),
                new Kontaktdaten("alice@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        patientService.createPatient(
                new Krankenkasse("AOK"), "K67891",
                new Personendaten("Bob", "Smith", "Mr", LocalDate.of(1982, 7, 20)),
                new Kontaktdaten("bob@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        List<Patient> patientsBornBefore1980 = patientService.findPatientsBornBefore(1980);
        assertThat(patientsBornBefore1980).hasSize(1);
        assertThat(patientsBornBefore1980.get(0).getPersonendaten().firstName()).isEqualTo("Alice");
    }
    @Test
    public void shouldCheckIfKrankenversicherungsnummerExists() {
        patientService.createPatient(
                new Krankenkasse("BKK"), "K87654",
                new Personendaten("Charlie", "Brown", "Mr", LocalDate.of(1992, 1, 15)),
                new Kontaktdaten("charlie@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        boolean exists = patientService.krankenversicherungsnummerExists("K87654");
        boolean notExists = patientService.krankenversicherungsnummerExists("K99999");
        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }
    @Test
    public void shouldDeletePatient() {
        Patient patient = patientService.createPatient(
                new Krankenkasse("TK"), "K54321",
                new Personendaten("Alice", "Miller", "Dr", LocalDate.of(1975, 3, 25)),
                new Kontaktdaten("alice@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        patientService.deletePatient(patient.getId());
        assertThat(patientService.findPatientById(patient.getId())).isNotPresent();
    }
    @Test
    public void shouldDeleteAllPatients() {
        patientService.createPatient(
                new Krankenkasse("TK"), "K54321",
                new Personendaten("Alice", "Miller", "Dr", LocalDate.of(1975, 3, 25)),
                new Kontaktdaten("alice@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        patientService.createPatient(
                new Krankenkasse("AOK"), "K67891",
                new Personendaten("Bob", "Smith", "Mr", LocalDate.of(1982, 7, 20)),
                new Kontaktdaten("bob@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt"));
        patientService.deleteAllPatients();
        assertThat(patientService.findPatientsByKrankenkasse("TK")).isEmpty();
        assertThat(patientService.findPatientsByKrankenkasse("AOK")).isEmpty();
    }
    @Test
    public void shouldUpdatePatient() {
        Patient patient = new Patient(
                null,
                new Krankenkasse("BKK"), "K67891",
                new Personendaten("Eve", "Williams", "Dr", LocalDate.of(1995, 7, 7)),
                new Kontaktdaten("eve@example.com", "+491234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
        patientService.createPatient(patient);
        patient.setKontaktdaten(new Kontaktdaten("eve@example.com","+321654789"));
        var updatedPatient = patientService.updatePatient(patient.getId(), patient);
        assertThat(updatedPatient).isPresent();
        assertThat(updatedPatient.get().getKontaktdaten().telefon()).isEqualTo("+321654789");
    }

     */
}