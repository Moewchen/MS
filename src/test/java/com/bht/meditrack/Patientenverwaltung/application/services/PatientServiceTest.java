package com.bht.meditrack.Patientenverwaltung.application.services;

import com.bht.meditrack.Patientenverwaltung.domain.events.PatientEntferntEvent;
import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.Patientenverwaltung.domain.valueojects.Krankenkasse1;
import com.bht.meditrack.Patientenverwaltung.infrastructure.repositories.PatientRepository;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("PatientService Tests")
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private PatientService patientService;

    private UUID patientId;
    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        patientId = UUID.randomUUID();
        patient = new Patient();
        patient.setId(patientId);
        patient.setPersonendaten(new Personendaten("John", "Doe", "Mr", LocalDate.of(1980, 1, 1)));
        patient.setKrankenversicherungsnummer("123456789");
        patient.setKrankenkasse(new Krankenkasse("AOK"));
        patient.setKontaktdaten(new Kontaktdaten("john.doe@example.com", "0123456789"));

        // Default repository behavior
        when(patientRepository.save(any(Patient.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void testCreatePatient() {
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        Patient createdPatient = patientService.upsertPatient(patientId, patient);
        assertNotNull(createdPatient, "Patient should not be null");
        assertEquals(patientId, createdPatient.getId(), "Patient ID should match");
    }

    @Test
    void testFindById() {
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        Optional<Patient> foundPatient = patientService.findById(patientId);
        assertTrue(foundPatient.isPresent(), "Patient should be found");
        assertEquals(patientId, foundPatient.get().getId(), "Patient ID should match");
    }

    @Test
    void testFindByIdNotFound() {
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());
        Optional<Patient> foundPatient = patientService.findById(patientId);
        assertFalse(foundPatient.isPresent(), "Patient should not be found");
    }

    @Test
    void testGetAllPatients() {
        when(patientRepository.findAll()).thenReturn(List.of(patient));
        List<Patient> patients = patientService.getAllPatients();
        assertNotNull(patients, "Patient list should not be null");
        assertEquals(1, patients.size(), "There should be exactly one patient in the list");
    }

    @Test
    void testUpdatePatient() {
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        Patient updatedPatient = patientService.upsertPatient(patientId, patient);
        assertNotNull(updatedPatient, "Updated patient should not be null");
        assertEquals(patientId, updatedPatient.getId(), "Patient ID should match");
    }

    @Test
    void testDeletePatient() {
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        doNothing().when(patientRepository).deleteById(patientId);
        doNothing().when(eventPublisher).publishEvent(any(PatientEntferntEvent.class));

        patientService.deletePatient(patientId);

        verify(patientRepository, times(1)).deleteById(patientId);
        verify(eventPublisher, times(1)).publishEvent(any(PatientEntferntEvent.class));
    }

    @Test
    void testDeletePatientNotFound() {
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            patientService.deletePatient(patientId);
        });

        assertEquals(String.format("Patient mit ID %s nicht gefunden", patientId), exception.getMessage());
    }
}
