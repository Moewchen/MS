package com.bht.meditrack.patientenverwaltung.ui.controller;

import com.bht.meditrack.patientenverwaltung.application.services.PatientService;
import com.bht.meditrack.patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    private Patient patient;
    private UUID patientId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientId = UUID.randomUUID();
        patient = new Patient();
        patient.setId(patientId);
    }

    @Test
    void createPatient() {
        // Set up the patient object
        patient.setId(patientId);
        patient.setKrankenversicherungsnummer("123456789");
        patient.setPersonendaten(new Personendaten("John", "Doe", "Mr", LocalDate.of(1980, 1, 1)));
        patient.setKontaktdaten(new Kontaktdaten("john.doe@example.com", "0123456789"));
        patient.setAdresse(new Adresse("Musterstraße", "1", "12345", "Musterstadt"));

        // Mock the patient service
        when(patientService.upsertPatient(any(UUID.class), any(Patient.class))).thenReturn(Optional.ofNullable(patient));

        // Call the controller method
        ResponseEntity<Optional<Patient>> response = patientController.createPatient(patient);

        // Log the response for debugging
        System.out.println("Response: " + response);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(patientId, response.getBody().get().getId());
    }

    @Test
    void updatePatient() {
        when(patientService.upsertPatient(any(UUID.class), any(Patient.class))).thenReturn(Optional.ofNullable(patient));

        ResponseEntity<Optional<Patient>> response = patientController.updatePatient(patientId, patient);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(patientId, response.getBody().get().getId());
    }

    @Test
    void getAllPatients() {
        when(patientService.getAllPatients()).thenReturn(List.of(patient));

        ResponseEntity<List<Patient>> response = patientController.getAllPatients();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }


    @Test
    void getPatientById() {
        // Mock the security context and authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Mock the JWT token
        Jwt jwt = mock(Jwt.class);
        when(authentication.getPrincipal()).thenReturn(jwt);
        when(jwt.getClaim("preferred_username")).thenReturn("test_user");

        // Mock the patient service
        when(patientService.findById(patientId)).thenReturn(Optional.of(patient));

        // Call the controller method
        ResponseEntity<Patient> response = patientController.getPatientById(patientId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(patientId, response.getBody().getId());
    }
    @Test
    void getPatientsByKrankenkasse() {
        String krankenkasse = "AOK";
        when(patientService.findByKrankenkasse(krankenkasse)).thenReturn(List.of(patient));

        ResponseEntity<List<Patient>> response = patientController.getPatientsByKrankenkasse(krankenkasse);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }
}
