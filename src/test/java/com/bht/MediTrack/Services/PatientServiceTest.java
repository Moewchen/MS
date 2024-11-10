package com.bht.MediTrack.Services;
import com.bht.MediTrack.Entities.Patient;
import com.bht.MediTrack.Repositories.InMemoryPatientRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PatientServiceTest {

    private PatientService patientService;
    private InMemoryPatientRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryPatientRepository();
        patientService = new PatientService(repository);
    }


    @Test
    public void testCreatePatient_NullName_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            patientService.createPatient(null, "Muster", "Dr.", LocalDate.of(2015, 1, 1), "+491234567890", "muster@example.com", "Straße 1", "AOK", "123456");
        });
        assertEquals("Pflichtfelder dürfen nicht null sein.", exception.getMessage());
    }

    @Test
    public void shouldCreateAndFindPatientById() {
        Patient patient = patientService.createPatient("Alice", "Miller", "Dr", LocalDate.of(1975, 3, 25),
                "987654", "alice@example.com", "Address 3",
                "TK", "K54321");

        Optional<Patient> foundPatient = patientService.findPatientById(patient.getId());
        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get().getLastName()).isEqualTo("Miller");
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
        Patient patient = new Patient("Bob", "Anderson", "Mr", LocalDate.of(1991, 6, 15),
                "123987", "bob@example.com", "Address 2", "BKK", "K54321");
        patientService.createPatient(patient);

        var foundPatients = patientService.getPatientByName("Bob");

        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getLastName()).isEqualTo("Anderson");
    }

    @Test
    public void shouldRetrievePatientByGeburtsdatum() {
        Patient patient = new Patient("Charlie", "Thompson", "Mr", LocalDate.of(1978, 10, 10),
                "789456", "charlie@example.com", "Address 3", "TK", "K32109");
        patientService.createPatient(patient);

        var foundPatients = patientService.getPatientByGeburtsdatum(LocalDate.of(1978, 10, 10));

        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getFirstName()).isEqualTo("Charlie");
    }

    @Test
    public void shouldFindPatientsByKrankenkasse() {
        patientService.createPatient("Alice", "Miller", "Dr", LocalDate.of(1975, 3, 25),
                "987654", "alice@example.com", "Address 3", "TK", "K54321");
        patientService.createPatient("Bob", "Smith", "Mr", LocalDate.of(1982, 7, 20),
                "123123", "bob@example.com", "Address 4", "AOK", "K67891");

        List<Patient> tkPatients = patientService.findPatientsByKrankenkasse("TK");

        assertThat(tkPatients).hasSize(1);
        assertThat(tkPatients.get(0).getFirstName()).isEqualTo("Alice");
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
        Patient patient = patientService.createPatient("Emily", "Clark", "Dr.", LocalDate.of(2000, 12, 20), "+4912345670", "emily@example.com", "Bergstraße 2", "DAK", "112233");
        patientService.deletePatient(patient.getId());
        Optional<Patient> deletedPatient = patientService.findPatientById(patient.getId());
        assertFalse(deletedPatient.isPresent());
    }

    @Test
    public void shouldFindPatientsBornBefore() {
        patientService.createPatient("Alice", "Miller", "Dr", LocalDate.of(1975, 3, 25),
                "987654", "alice@example.com", "Address 3", "TK", "K54321");
        patientService.createPatient("Bob", "Smith", "Mr", LocalDate.of(1985, 10, 10),
                "123123", "bob@example.com", "Address 4", "AOK", "K67891");

        List<Patient> patientsBornBefore1980 = patientService.findPatientsBornBefore(1980);

        assertThat(patientsBornBefore1980).hasSize(1);
        assertThat(patientsBornBefore1980.get(0).getFirstName()).isEqualTo("Alice");
    }

    @Test
    public void shouldCheckIfKrankenversicherungsnummerExists() {
        patientService.createPatient("Charlie", "Brown", "Mr", LocalDate.of(1992, 1, 15),
                "789789", "charlie@example.com", "Address 5", "BKK", "K87654");

        boolean exists = patientService.krankenversicherungsnummerExists("K87654");
        boolean notExists = patientService.krankenversicherungsnummerExists("K99999");

        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }

    @Test
    public void shouldDeletePatient() {
        Patient patient = patientService.createPatient("Alice", "Miller", "Dr", LocalDate.of(1975, 3, 25),
                "987654", "alice@example.com", "Address 3", "TK", "K54321");
        patientService.deletePatient(patient.getId());

        assertThat(patientService.findPatientById(patient.getId())).isNotPresent();
    }

    @Test
    public void shouldDeleteAllPatients() {
        patientService.createPatient("Alice", "Miller", "Dr", LocalDate.of(1975, 3, 25),
                "987654", "alice@example.com", "Address 3", "TK", "K54321");
        patientService.createPatient("Bob", "Smith", "Mr", LocalDate.of(1985, 10, 10),
                "123123", "bob@example.com", "Address 4", "AOK", "K67891");

        patientService.deleteAllPatients();

        assertThat(patientService.findPatientsByKrankenkasse("TK")).isEmpty();
        assertThat(patientService.findPatientsByKrankenkasse("AOK")).isEmpty();
    }

    @Test
    public void shouldUpdatePatient() {
        Patient patient = new Patient("Eve", "Williams", "Dr", LocalDate.of(1995, 7, 7),
                "321654", "eve@example.com", "Address 5", "BKK", "K65487");
        patientService.createPatient(patient);

        patient.setTelefon("321654789");
        var updatedPatient = patientService.updatePatient(patient.getId(), patient);

        assertThat(updatedPatient).isPresent();
        assertThat(updatedPatient.get().getTelefon()).isEqualTo("321654789");
    }
}
