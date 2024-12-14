package com.bht.MediTrack.Vitaldatenmanagement.application.services;

import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.PublisherEvent;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import com.bht.MediTrack.Vitaldatenmanagement.exceptions.InvalidVitaldatenException;
import com.bht.MediTrack.Vitaldatenmanagement.exceptions.VitaldatenNotFoundException;
import com.bht.MediTrack.Vitaldatenmanagement.infrastructure.repositories.VitaldatenRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class VitaldatenServiceTest {

    @Mock
    private VitaldatenRepository vitaldatenRepository;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Mock
    private PublisherEvent eventListener; // Mock the PublisherEvent

    @InjectMocks
    private VitaldatenService vitaldatenService;
    private UUID patientId;
    private Vitaldaten vitaldaten;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientId = UUID.randomUUID();
        vitaldaten = new Vitaldaten();
        vitaldaten.setId(UUID.randomUUID());
        vitaldaten.setHerzfrequenz((short) 75);
        vitaldaten.setAtemfrequenz((byte) 16);
        vitaldaten.setSystolisch((short) 120);
        vitaldaten.setDiastolisch((short) 80);
        vitaldaten.setTemperatur(37.0f);
        vitaldaten.setDatum(LocalDateTime.now());
    }

    @Test
    void testFindByPatientId() {
        when(vitaldatenRepository.findByPatientId(patientId)).thenReturn(List.of(vitaldaten));
        List<Vitaldaten> result = vitaldatenService.findByPatientId(patientId);
        assertFalse(result.isEmpty());
        assertEquals(vitaldaten, result.get(0));
    }

    @Test
    void testGetVitaldatenWithStreams() {
        List<Vitaldaten> vitaldatenList = Stream.of(vitaldaten, new Vitaldaten(), new Vitaldaten())
                .peek(v -> v.setId(UUID.randomUUID()))
                .collect(Collectors.toList());
        when(vitaldatenRepository.findById(any(UUID.class)))
                .thenAnswer(invocation -> {
                    UUID id = invocation.getArgument(0);
                    return vitaldatenList.stream()
                            .filter(v -> v.getId().equals(id))
                            .findFirst();
                });
        List<Optional<Vitaldaten>> results = vitaldatenList.stream()
                .map(v -> vitaldatenService.findById(v.getId()))
                .collect(Collectors.toList());
        assertEquals(vitaldatenList.size(), results.size());
        results.forEach(result -> assertTrue(result.isPresent()));
    }

    @Nested
    class CreateVitaldatenTests {

        @Test
        void shouldThrowExceptionWhenVitaldatenIsNull() {
            assertThrows(InvalidVitaldatenException.class, () ->
                    vitaldatenService.upsertVitaldaten(patientId, null));
        }

        @Test
        void shouldThrowExceptionWhenHerzfrequenzIsInvalid() {
            vitaldaten.setHerzfrequenz((short) 300);
            assertThrows(InvalidVitaldatenException.class, () ->
                    vitaldatenService.upsertVitaldaten(patientId, vitaldaten));
        }
    }

    @Nested
    class GetVitaldatenTests {
        @Test
        void shouldGetVitaldatenById() {
            when(vitaldatenRepository.findById(any(UUID.class))).thenReturn(Optional.of(vitaldaten));
            Optional<Vitaldaten> result = vitaldatenService.findById(vitaldaten.getId());
            assertTrue(result.isPresent());
            assertEquals(vitaldaten.getId(), result.get().getId());
        }

        @Test
        void shouldReturnEmptyOptionalWhenVitaldatenNotFound() {
            when(vitaldatenRepository.findById(any(UUID.class)))
                    .thenReturn(Optional.empty());
            Optional<Vitaldaten> result = vitaldatenService.findById(UUID.randomUUID());
            assertFalse(result.isPresent());
        }
    }

    @Nested
    class UpdateVitaldatenTests {
        @Test
        void shouldUpdateVitaldatenSuccessfully() {
            when(vitaldatenRepository.findById(any(UUID.class))).thenReturn(Optional.of(vitaldaten));
            when(vitaldatenRepository.save(any(Vitaldaten.class))).thenReturn(vitaldaten);
            Vitaldaten result = vitaldatenService.upsertVitaldaten(patientId, vitaldaten);

            assertNotNull(result);
            verify(vitaldatenRepository).save(vitaldaten);
        }

        @Test
        void shouldThrowExceptionWhenUpdatingNonExistentVitaldaten() {
            when(vitaldatenRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
            assertThrows(VitaldatenNotFoundException.class, () ->
                    vitaldatenService.upsertVitaldaten(patientId, vitaldaten));
        }

        @Test
        void shouldThrowExceptionWhenHerzfrequenzIsInvalid() {
            vitaldaten.setHerzfrequenz((short) 300);
            assertThrows(InvalidVitaldatenException.class, () ->
                    vitaldatenService.upsertVitaldaten(patientId, vitaldaten));
        }
    }

    @Nested
    class DeleteVitaldatenTests {
        @Test
        void shouldDeleteVitaldatenSuccessfully() {
            // Set the patient field
            Patient patient = new Patient();
            patient.setId(patientId);
            vitaldaten.setPatient(patient);

            when(vitaldatenRepository.findById(any(UUID.class))).thenReturn(Optional.of(vitaldaten));
            doNothing().when(vitaldatenRepository).deleteByPatientIdAndId(any(UUID.class), any(UUID.class));
            assertDoesNotThrow(() -> vitaldatenService.deleteVitaldaten(patientId, vitaldaten.getId()));
            verify(vitaldatenRepository).deleteByPatientIdAndId(patientId, vitaldaten.getId());
        }

        @Test
        void shouldThrowExceptionWhenDeletingNonExistentVitaldaten() {
            when(vitaldatenRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
            assertThrows(VitaldatenNotFoundException.class, () ->
                    vitaldatenService.deleteVitaldaten(patientId, UUID.randomUUID()));
        }
    }

    /* ************ UNIT TESTS ************ */
    @Test
    void shouldCreateVitaldatenSuccessfully() {
        when(vitaldatenRepository.save(any(Vitaldaten.class))).thenAnswer(invocation -> invocation.getArgument(0));
        vitaldaten.setId(null); // Ensure the ID is null for creation
        Vitaldaten createdVitaldaten = vitaldatenService.upsertVitaldaten(patientId, vitaldaten);

        assertNotNull(createdVitaldaten);
        assertEquals(vitaldaten.getId(), createdVitaldaten.getId());
        verify(vitaldatenRepository, times(1)).save(vitaldaten);
    }

    //TODO kann raus, steht weiter oben
    @Test
    void shouldUpdateVitaldatenSuccessfully() {
        when(vitaldatenRepository.findById(any(UUID.class))).thenReturn(Optional.of(vitaldaten));
        when(vitaldatenRepository.save(any(Vitaldaten.class))).thenReturn(vitaldaten);

        Vitaldaten updatedVitaldaten = vitaldatenService.upsertVitaldaten(patientId, vitaldaten);

        assertNotNull(updatedVitaldaten);
        assertEquals(vitaldaten.getId(), updatedVitaldaten.getId());
        verify(vitaldatenRepository, times(1)).save(vitaldaten);
    }

    //TODO kann raus, steht weiter oben
    @Test
    void shouldDeleteVitaldatenSuccessfully() {
        // Set the patient field
        Patient patient = new Patient();
        patient.setId(patientId);
        vitaldaten.setPatient(patient);

        when(vitaldatenRepository.findById(any(UUID.class))).thenReturn(Optional.of(vitaldaten));
        doNothing().when(vitaldatenRepository).deleteByPatientIdAndId(any(UUID.class), any(UUID.class));
        assertDoesNotThrow(() -> vitaldatenService.deleteVitaldaten(patientId, vitaldaten.getId()));
        verify(vitaldatenRepository).deleteByPatientIdAndId(patientId, vitaldaten.getId());
    }

}