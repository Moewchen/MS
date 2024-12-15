package com.bht.MediTrack.Patientenverwaltung.application.services;

import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.MediTrack.Patientenverwaltung.infrastructure.repositories.PatientRepository;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;
    private UUID patientId;

    @BeforeEach
    void setUp() {
        patientId = UUID.randomUUID();
        patient = new Patient();
        patient.setId(patientId);
        patient.setPersonendaten(new Personendaten("John", "Doe", "Mr", LocalDate.of(1980, 1, 1)));
        patient.setKrankenversicherungsnummer("123456789");
        patient.setKrankenkasse(new Krankenkasse("AOK"));
        patient.setKontaktdaten(new Kontaktdaten("john.doe@example.com", "0123456789")); // Initialize Kontaktdaten
    }

    @Test
    void testCreatePatient() {
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient createdPatient = patientService.createPatient(patient);

        assertNotNull(createdPatient, "Patient should not be null");
        assertEquals(patientId, createdPatient.getId(), "Patient ID should match");
    }

    @Test
    void testFindById() {
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));

        Patient foundPatient = patientService.findById(patientId);

        assertNotNull(foundPatient, "Patient should be found");
        assertEquals(patientId, foundPatient.getId(), "Patient ID should match");
    }

    @Test
    void testFindByIdNotFound() {
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        Patient foundPatient = patientService.findById(patientId);

        assertNull(foundPatient, "Patient should not be found");
    }

    @Test
    void testGetAllPatients() {
        when(patientRepository.findAll()).thenReturn(List.of(patient));

        List<Patient> patients = patientService.getAllPatients();

        assertNotNull(patients, "Patient list should not be null");
        assertEquals(1, patients.size(), "There should be exactly one patient in the list");
    }

    @Test
    void testDeleteById() {
        doNothing().when(patientRepository).deleteById(patientId);

        patientService.deletePatient(patientId);

        verify(patientRepository, times(1)).deleteById(patientId);
    }

    @Test
    void testUpdatePatient() {
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Optional<Patient> updatedPatient = patientService.updatePatient(patientId, patient);

        assertTrue(updatedPatient.isPresent(), "Updated patient should be present");
        assertEquals(patientId, updatedPatient.get().getId(), "Patient ID should match");
    }
}
