package com.bht.meditrack.vitaldatenmanagement.application.services;

import com.bht.meditrack.patientenverwaltung.infrastructure.persistence.PatientEntity;
import com.bht.meditrack.patientenverwaltung.infrastructure.persistence.PatientMapper;
import com.bht.meditrack.patientenverwaltung.infrastructure.repositories.PatientRepository;
import com.bht.meditrack.PublisherEvent;
import com.bht.meditrack.vitaldatenmanagement.domain.events.VitaldatenErstelltEvent;
import com.bht.meditrack.vitaldatenmanagement.domain.model.Vitaldaten;
import com.bht.meditrack.vitaldatenmanagement.exceptions.InvalidVitaldatenException;
import com.bht.meditrack.vitaldatenmanagement.exceptions.VitaldatenNotFoundException;
import com.bht.meditrack.vitaldatenmanagement.infrastructure.persistence.VitaldatenEntity;
import com.bht.meditrack.vitaldatenmanagement.infrastructure.persistence.VitaldatenMapper;
import com.bht.meditrack.vitaldatenmanagement.infrastructure.repositories.VitaldatenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("VitaldatenService Tests")
class VitaldatenServiceTest {

    @Mock
    private VitaldatenRepository vitaldatenRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PublisherEvent eventPublisher;

    @InjectMocks
    private VitaldatenService vitaldatenService;

    private UUID patientId;
    private PatientEntity patientEntity;
    private Vitaldaten validVitaldaten;
    private static final LocalDateTime TEST_DATUM = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        patientId = UUID.randomUUID();
        patientEntity = new PatientEntity();
        patientEntity.setId(patientId);

        validVitaldaten = createValidVitaldaten();
        validVitaldaten.setPatient(PatientMapper.toPatientDomain(patientEntity));

        // Default repository behavior
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patientEntity));
        when(vitaldatenRepository.save(any(VitaldatenEntity.class))).thenAnswer(i -> i.getArgument(0));
    }

    private Vitaldaten createValidVitaldaten() {
        Vitaldaten vitaldaten = new Vitaldaten();
        vitaldaten.setId(UUID.randomUUID());
        vitaldaten.setHerzfrequenz((short) 75);
        vitaldaten.setAtemfrequenz((byte) 16);
        vitaldaten.setSystolisch((short) 120);
        vitaldaten.setDiastolisch((short) 80);
        vitaldaten.setTemperatur(37.0f);
        vitaldaten.setDatum(TEST_DATUM);
        return vitaldaten;
    }

    @Nested
    @DisplayName("Create Vitaldaten Tests")
    class CreateVitaldatenTests {

        @Test
        @DisplayName("Should create valid vitaldaten successfully")
        void shouldCreateValidVitaldatenSuccessfully() {
            // Arrange
            Vitaldaten vitaldaten = createValidVitaldaten();
            vitaldaten.setId(null);

            // Act & Assert
            assertThrows(InvalidVitaldatenException.class, () ->
                            vitaldatenService.upsertVitaldaten(null, vitaldaten),
                    "Should throw InvalidVitaldatenException when patientId is null");
        }

        @ParameterizedTest
        @ValueSource(shorts = {19, 221}) // Testing boundary values
        @DisplayName("Should not create vitaldaten with invalid herzfrequenz")
        void shouldNotCreateVitaldatenWithInvalidHerzfrequenz(short herzfrequenz) {
            // Arrange
            Vitaldaten vitaldaten = createValidVitaldaten();
            vitaldaten.setHerzfrequenz(herzfrequenz);

            // Act & Assert
            InvalidVitaldatenException exception = assertThrows(
                    InvalidVitaldatenException.class,
                    () -> vitaldatenService.upsertVitaldaten(UUID.randomUUID(), vitaldaten)
            );

            assertEquals(
                    String.format("Herzfrequenz muss zwischen %d und %d liegen, war: %d",
                            20, 220, herzfrequenz),
                    exception.getMessage()
            );
        }

        @ParameterizedTest
        @ValueSource(bytes = {4, 61}) // Testing boundary values
        @DisplayName("Should not create vitaldaten with invalid atemfrequenz")
        void shouldNotCreateVitaldatenWithInvalidAtemfrequenz(byte atemfrequenz) {
            Vitaldaten vitaldaten = createValidVitaldaten();
            vitaldaten.setAtemfrequenz(atemfrequenz);
            InvalidVitaldatenException exception = assertThrows(
                    InvalidVitaldatenException.class,
                    () -> vitaldatenService.upsertVitaldaten(UUID.randomUUID(), vitaldaten)
            );

            assertEquals(
                    String.format("Atemfrequenz muss zwischen %d und %d liegen, war: %d",
                            5, 60, atemfrequenz),
                    exception.getMessage()
            );
        }

        @ParameterizedTest
        @ValueSource(shorts = {49, 251}) // Testing boundary values
        @DisplayName("Should not create vitaldaten with invalid systolisch")
        void shouldNotCreateVitaldatenWithInvalidSystolisch(short systolisch) {
            Vitaldaten vitaldaten = createValidVitaldaten();
            vitaldaten.setSystolisch(systolisch);
            InvalidVitaldatenException exception = assertThrows(
                    InvalidVitaldatenException.class,
                    () -> vitaldatenService.upsertVitaldaten(UUID.randomUUID(), vitaldaten)
            );

            assertEquals(
                    String.format("Systolisch muss zwischen %d und %d liegen, war: %d",
                            50, 250, systolisch),
                    exception.getMessage()
            );
        }

        @ParameterizedTest
        @ValueSource(shorts = {39, 151}) // Testing boundary values
        @DisplayName("Should not create vitaldaten with invalid diastolisch")
        void shouldNotCreateVitaldatenWithInvalidDiastolisch(short diastolisch) {
            Vitaldaten vitaldaten = createValidVitaldaten();
            vitaldaten.setDiastolisch(diastolisch);
            InvalidVitaldatenException exception = assertThrows(
                    InvalidVitaldatenException.class,
                    () -> vitaldatenService.upsertVitaldaten(UUID.randomUUID(), vitaldaten)
            );

            assertEquals(
                    String.format("Diastolisch muss zwischen %d und %d liegen, war: %d",
                            40, 150, diastolisch),
                    exception.getMessage()
            );
        }

        @ParameterizedTest
        @ValueSource(floats = {29.9f, 45.1f}) // Testing boundary values
        @DisplayName("Should not create vitaldaten with invalid temperatur")
        void shouldNotCreateVitaldatenWithInvalidTemperatur(float temperatur) {
            Vitaldaten vitaldaten = createValidVitaldaten();
            vitaldaten.setTemperatur(temperatur);
            InvalidVitaldatenException exception = assertThrows(
                    InvalidVitaldatenException.class,
                    () -> vitaldatenService.upsertVitaldaten(UUID.randomUUID(), vitaldaten)
            );

            assertEquals(
                    String.format("Temperatur muss zwischen %.1f und %.1f liegen, war: %.1f",
                            30.0f, 45.0f, temperatur),
                    exception.getMessage()
            );
        }
    }

    @Nested
    @DisplayName("Update Vitaldaten Tests")
    class UpdateVitaldatenTests {

        @Test
        @DisplayName("Should update existing vitaldaten successfully")
        void shouldUpdateExistingVitaldatenSuccessfully() {
            UUID existingId = UUID.randomUUID();
            validVitaldaten.setId(existingId);
            VitaldatenEntity existingEntity = VitaldatenMapper.toVitaldatenEntity(validVitaldaten.getId(), validVitaldaten);
            when(vitaldatenRepository.findById(existingId)).thenReturn(Optional.of(existingEntity));
            when(vitaldatenRepository.save(any(VitaldatenEntity.class))).thenReturn(existingEntity);
            Optional<Vitaldaten> result = vitaldatenService.upsertVitaldaten(patientId, validVitaldaten);
            assertTrue(result.isPresent());
            assertEquals(existingId, result.get().getId());
            verify(eventPublisher).publishEvent(any(VitaldatenErstelltEvent.class));
        }

        @Test
        @DisplayName("Should throw exception when updating non-existent vitaldaten")
        void shouldThrowExceptionWhenUpdatingNonExistentVitaldaten() {
            UUID nonExistentId = UUID.randomUUID();
            validVitaldaten.setId(nonExistentId);
            when(vitaldatenRepository.findById(nonExistentId)).thenReturn(Optional.empty());
            VitaldatenNotFoundException exception = assertThrows(VitaldatenNotFoundException.class,
                    () -> vitaldatenService.upsertVitaldaten(patientId, validVitaldaten));
            assertEquals("Vitaldaten mit ID " + nonExistentId + " nicht gefunden", exception.getMessage());
            verify(vitaldatenRepository, never()).save(any());
            verify(eventPublisher, never()).publishEvent(any());
        }

        @Test
        @DisplayName("Should throw exception when vitaldaten have invalid herzfrequenz")
        void shouldThrowExceptionWhenVitaldatenHaveInvalidHerzfrequenz() {
            validVitaldaten.setHerzfrequenz((short) 250); // Ungültiger Wert
            InvalidVitaldatenException exception = assertThrows(InvalidVitaldatenException.class,
                    () -> vitaldatenService.upsertVitaldaten(patientId, validVitaldaten));

            assertTrue(exception.getMessage().contains("Herzfrequenz"));
            verify(vitaldatenRepository, never()).save(any());
            verify(eventPublisher, never()).publishEvent(any());
        }
    }

    @Nested
    @DisplayName("Find Vitaldaten Tests")
    class FindVitaldatenTests {

        @Test
        @DisplayName("Should find vitaldaten by patient ID")
        void shouldFindVitaldatenByPatientId() {
            // Arrange
            VitaldatenEntity validVitaldatenEntity = VitaldatenMapper.toVitaldatenEntity(validVitaldaten.getId(), validVitaldaten);
            VitaldatenEntity anotherVitaldatenEntity = VitaldatenMapper.toVitaldatenEntity(createValidVitaldaten().getId(), createValidVitaldaten());
            List<VitaldatenEntity> vitaldatenEntities = Arrays.asList(validVitaldatenEntity, anotherVitaldatenEntity);
            List<Vitaldaten> expectedVitaldaten = VitaldatenMapper.toVitaldatenDomainList(vitaldatenEntities);
            when(vitaldatenRepository.findByPatientId(patientId)).thenReturn(vitaldatenEntities);
            List<Vitaldaten> result = vitaldatenService.findByPatientId(patientId);
            assertEquals(expectedVitaldaten.size(), result.size());
        }

        @Test
        @DisplayName("Should return empty list when no vitaldaten found for patient")
        void shouldReturnEmptyListWhenNoVitaldatenFound() {
            when(vitaldatenRepository.findByPatientId(patientId)).thenReturn(List.of());
            List<Vitaldaten> result = vitaldatenService.findByPatientId(patientId);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should find vitaldaten by ID")
        void shouldFindVitaldatenById() {
            VitaldatenEntity validVitaldatenEntity = VitaldatenMapper.toVitaldatenEntity(validVitaldaten.getId(), validVitaldaten);
            when(vitaldatenRepository.findById(validVitaldaten.getId())).thenReturn(Optional.of(validVitaldatenEntity));
            Optional<Vitaldaten> result = vitaldatenService.findById(validVitaldaten.getId());
            assertTrue(result.isPresent());
            assertEquals(validVitaldaten.getId(), result.get().getId());
        }
    }

    @Nested
    @DisplayName("Delete Vitaldaten Tests")
    class DeleteVitaldatenTests {

        @Test
        @DisplayName("Should delete existing vitaldaten successfully")
        void shouldDeleteExistingVitaldatenSuccessfully() {
            VitaldatenEntity validVitaldatenEntity = VitaldatenMapper.toVitaldatenEntity(validVitaldaten.getId(), validVitaldaten);
            validVitaldatenEntity.setPatient(patientEntity);
            when(vitaldatenRepository.findById(validVitaldaten.getId())).thenReturn(Optional.of(validVitaldatenEntity));
            assertDoesNotThrow(() ->
                    vitaldatenService.deleteVitaldaten(patientId, validVitaldaten.getId()));
            verify(vitaldatenRepository).deleteByPatientIdAndId(patientId, validVitaldaten.getId());
        }

        @Test
        @DisplayName("Should throw exception when deleting non-existent vitaldaten")
        void shouldThrowExceptionWhenDeletingNonExistentVitaldaten() {
            UUID nonExistentId = UUID.randomUUID();
            when(vitaldatenRepository.findById(nonExistentId)).thenReturn(Optional.empty());
            assertThrows(VitaldatenNotFoundException.class,
                    () -> vitaldatenService.deleteVitaldaten(patientId, nonExistentId));
            verify(vitaldatenRepository, never()).deleteByPatientIdAndId(any(), any());
        }

        @Test
        @DisplayName("Should throw exception when deleting vitaldaten with wrong patient ID")
        void shouldThrowExceptionWhenDeletingVitaldatenWithWrongPatientId() {
            UUID wrongPatientId = UUID.randomUUID();
            VitaldatenEntity validVitaldatenEntity = VitaldatenMapper.toVitaldatenEntity(validVitaldaten.getId(), validVitaldaten);
            validVitaldatenEntity.setPatient(patientEntity);
            when(vitaldatenRepository.findById(validVitaldatenEntity.getId()))
                    .thenReturn(Optional.of(validVitaldatenEntity));
            assertThrows(VitaldatenNotFoundException.class,
                    () -> vitaldatenService.deleteVitaldaten(wrongPatientId, validVitaldaten.getId())
            );
            verify(vitaldatenRepository, never()).deleteByPatientIdAndId(any(), any());
        }
    }

    @Nested
    @DisplayName("Event Publication Tests")
    class EventPublicationTests {

        @Test
        @DisplayName("Should publish event when creating vitaldaten")
        void shouldPublishEventWhenCreatingVitaldaten() {
            Vitaldaten newVitaldaten = createValidVitaldaten();
            newVitaldaten.setId(null); // Wichtig: Setze ID auf null für neue Vitaldaten
            newVitaldaten.setPatient(PatientMapper.toPatientDomain(patientEntity));
            VitaldatenEntity savedVitaldatenEntity = VitaldatenMapper.toVitaldatenEntity(validVitaldaten.getId(), validVitaldaten);
            when(vitaldatenRepository.save(any(VitaldatenEntity.class))).thenReturn(savedVitaldatenEntity);
            ArgumentCaptor<VitaldatenErstelltEvent> eventCaptor = ArgumentCaptor.forClass(VitaldatenErstelltEvent.class);
            Optional<Vitaldaten> result = vitaldatenService.upsertVitaldaten(patientId, newVitaldaten);
            assertTrue(result.isPresent());
            verify(eventPublisher).publishEvent(eventCaptor.capture());
            VitaldatenErstelltEvent capturedEvent = eventCaptor.getValue();
            assertEquals(validVitaldaten.getId(), capturedEvent.getId());
            assertEquals(validVitaldaten.getHerzfrequenz(), capturedEvent.getHerzfrequenz());
        }
    }
}
