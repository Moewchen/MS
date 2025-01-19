package com.bht.meditrack.Patientenverwaltung.infrastructure.repositories;

import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.Patientenverwaltung.domain.valueojects.Krankenkasse1;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Reset the database after each test
@Transactional
class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    private Patient patient;
    private UUID patientId;

    @BeforeEach
    void setUp() {
        patientId = UUID.randomUUID();
        patient = new Patient();
        patient.setId(patientId);
        patient.setKrankenkasse(new Krankenkasse1("AOK"));
        patient.setKrankenversicherungsnummer("123456789012");
        patient.setPersonendaten(new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)));
        patient.setKontaktdaten(new Kontaktdaten("max.mustermann@example.com", "01234567890"));
        patient.setAdresse(new Adresse("Musterstraße", "1", "12345", "Musterstadt"));
        patientRepository.save(patient);
        System.out.println("Patient saved with ID: " + patientId);
    }

    @Test
    void findByKrankenkasse() {
        List<Patient> patients = patientRepository.findByKrankenkasse(new Krankenkasse("AOK"));
        assertFalse(patients.isEmpty());
        assertEquals("AOK", patients.get(0).getKrankenkasse().krankenkasse());
    }

    @Test
    void deleteById() {
        patientRepository.deleteById(patientId);
        Optional<Patient> deletedPatient = patientRepository.findById(patientId);
        assertFalse(deletedPatient.isPresent());
    }
}
