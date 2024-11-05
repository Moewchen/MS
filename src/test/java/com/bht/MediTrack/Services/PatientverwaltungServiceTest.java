package com.bht.MediTrack.Services;
import com.bht.MediTrack.Entities.Patient;
import com.bht.MediTrack.Repositories.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


class PatientverwaltungServiceTest {

    @InjectMocks
    private PatientverwaltungService patientverwaltungService;

    @Mock
    private PatientRepository patientRepository;

    private Patient patient;
    private UUID patientId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientId = UUID.randomUUID();
        patient = new Patient("John", "Doe", "Mr.", null, "john.doe@example.com", "+123456789", null, null);
    }

    @Test
    void testCreatePatient() {
        when(patientRepository.create(any(Patient.class))).thenReturn(patient);
        Patient createdPatient = patientverwaltungService.createPatient(patient);

        assertThat(createdPatient).isNotNull();
        assertThat(createdPatient.getFirstName()).isEqualTo("John");
        verify(patientRepository, times(1)).create(patient);
    }


    @Test
    void testGetPatientById() {
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        Patient foundPatient = patientverwaltungService.getPatientById(patientId);
        assertThat(foundPatient).isNotNull();  // Check that foundPatient is not null
        assertThat(foundPatient.getFirstName()).isEqualTo("John");  // Example assertion
        verify(patientRepository, times(1)).findById(patientId); // Verify that the repository was called
    }

    @Test
    void testGetPatientByName() {
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        when(patientRepository.findPatientByName("John", "Doe")).thenReturn(patientList);
        List<Patient> foundPatients = patientverwaltungService.getPatientByName("John", "Doe");
        assertThat(foundPatients).isNotEmpty();
        assertThat(foundPatients.size()).isEqualTo(1);
        assertThat(foundPatients.get(0).getFirstName()).isEqualTo("John");
        verify(patientRepository, times(1)).findPatientByName("John", "Doe");
    }

    @Test
    void testGetPatientByGeburtsdatum() {
        Date geburtsdatum = new Date();
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        when(patientRepository.findPatientByGeburtsdatum(geburtsdatum)).thenReturn(patientList);
        List<Patient> foundPatients = patientverwaltungService.getPatientByGeburtsdatum(geburtsdatum);
        assertThat(foundPatients).isNotEmpty();
        assertThat(foundPatients.size()).isEqualTo(1);
        assertThat(foundPatients.get(0).getFirstName()).isEqualTo("John");
        verify(patientRepository, times(1)).findPatientByGeburtsdatum(geburtsdatum);
    }

    @Test
    void testUpdatePatient() {
        Patient updatedPatient = new Patient("Jane", "Doe", "Mrs.", null, "jane.doe@example.com", "+987654321", null, null);
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        when(patientRepository.create(any(Patient.class))).thenReturn(updatedPatient);
        Patient result = patientverwaltungService.updatePatient(patientId, updatedPatient);
        assertThat(result.getFirstName()).isEqualTo("Jane");
        assertThat(result.getLastName()).isEqualTo("Doe");
        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, times(1)).create(any(Patient.class));
    }

    @Test
    void testUpdatePatientNotFound() {
        Patient updatedPatient = new Patient("Jane", "Doe", "Mrs.", null, "jane.doe@example.com", "+987654321", null, null);
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());
        RuntimeException thrown = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            patientverwaltungService.updatePatient(patientId, updatedPatient);
        });
        assertThat(thrown.getMessage()).isEqualTo("Patient not found with id " + patientId);
        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, never()).create(any(Patient.class));
    }

    @Test
    void testDeletePatient() {
        doNothing().when(patientRepository).deleteById(patientId);
        patientverwaltungService.deletePatient(patientId);
        verify(patientRepository, times(1)).deleteById(patientId);
    }
}
