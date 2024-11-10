package com.bht.MediTrack.Services;

import com.bht.MediTrack.Entities.Vitaldaten;
import com.bht.MediTrack.Repositories.VitaldatenRepository;
import com.bht.MediTrack.Exceptions.InvalidVitaldatenException;
import com.bht.MediTrack.Exceptions.VitaldatenNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void testGetVitaldatenByPatientenId() {
        when(vitaldatenRepository.getVitaldatenByPatientenId(patientId)).thenReturn(Optional.of(vitaldaten));

        Optional<Vitaldaten> result = vitaldatenService.getVitaldatenByPatientenId(patientId);

        assertTrue(result.isPresent());
        assertEquals(vitaldaten, result.get());
    }

    @Test
    void testGetVitaldatenById() {

        when(vitaldatenRepository.getVitaldatenById(patientId)).thenReturn(Optional.of(vitaldaten));

        Optional<Vitaldaten> result = vitaldatenService.getVitaldatenById(patientId);

        assertTrue(result.isPresent());
        assertEquals(vitaldaten, result.get());
    }

    @Test
    void testUpdateVitaldaten() {
        when(vitaldatenRepository.getVitaldatenById(any(UUID.class))).thenReturn(Optional.of(vitaldaten));
        
        when(vitaldatenRepository.updateVitaldaten(patientId, vitaldaten)).thenReturn(true);

        boolean result = vitaldatenService.updateVitaldaten(patientId, vitaldaten);

        assertTrue(result);
    }

    @Test
    void testCreateVitaldaten() {
        when(vitaldatenRepository.createVitaldaten(patientId, vitaldaten)).thenReturn(vitaldaten);

        Vitaldaten result = vitaldatenService.createVitaldaten(patientId, vitaldaten);

        assertEquals(vitaldaten, result);
    }

    @Test
    void testGetVitaldatenWithStreams() {
        List<Vitaldaten> vitaldatenList = Stream.of(vitaldaten, new Vitaldaten(), new Vitaldaten())
                .peek(v -> v.setId(UUID.randomUUID()))
                .collect(Collectors.toList());

        when(vitaldatenRepository.getVitaldatenById(any(UUID.class)))
                .thenAnswer(invocation -> {
                    UUID id = invocation.getArgument(0);
                    return vitaldatenList.stream()
                            .filter(v -> v.getId().equals(id))
                            .findFirst();
                });

        List<Optional<Vitaldaten>> results = vitaldatenList.stream()
                .map(v -> vitaldatenService.getVitaldatenById(v.getId()))
                .collect(Collectors.toList());

        assertEquals(vitaldatenList.size(), results.size());
        results.forEach(result -> assertTrue(result.isPresent()));
    }

    @Nested
    class CreateVitaldatenTests {
        @Test
        void shouldCreateVitaldatenSuccessfully() {
        when(vitaldatenRepository.createVitaldaten(any(UUID.class), any(Vitaldaten.class))).thenReturn(vitaldaten);            
        
        Vitaldaten result = vitaldatenService.createVitaldaten(patientId, vitaldaten);
            assertNotNull(result);
            assertEquals(vitaldaten.getId(), result.getId());
            verify(vitaldatenRepository).createVitaldaten(patientId, vitaldaten);

        }

        @Test
        void shouldThrowExceptionWhenVitaldatenIsNull() {
            assertThrows(InvalidVitaldatenException.class, () -> 
                vitaldatenService.createVitaldaten(patientId, null));
        }

        @Test
        void shouldThrowExceptionWhenHerzfrequenzIsInvalid() {
            vitaldaten.setHerzfrequenz((short) 300);
            assertThrows(InvalidVitaldatenException.class, () -> 
                vitaldatenService.createVitaldaten(patientId, vitaldaten));
        }
    }

    @Nested
    class GetVitaldatenTests {
        @Test
        void shouldGetVitaldatenById() {
            when(vitaldatenRepository.getVitaldatenById(any(UUID.class))).thenReturn(Optional.of(vitaldaten));
            Optional<Vitaldaten> result = vitaldatenService.getVitaldatenById(vitaldaten.getId());
            assertTrue(result.isPresent());
            assertEquals(vitaldaten.getId(), result.get().getId());
        }

        @Test
        void shouldReturnEmptyOptionalWhenVitaldatenNotFound() {
            when(vitaldatenRepository.getVitaldatenById(any(UUID.class)))
                .thenReturn(Optional.empty());
            Optional<Vitaldaten> result = vitaldatenService.getVitaldatenById(UUID.randomUUID());
            assertFalse(result.isPresent());
        }
    }

    @Nested
    class UpdateVitaldatenTests {
        @Test
        void shouldUpdateVitaldatenSuccessfully() {
            when(vitaldatenRepository.getVitaldatenById(any(UUID.class))).thenReturn(Optional.of(vitaldaten));

            when(vitaldatenRepository.updateVitaldaten(any(UUID.class), any(Vitaldaten.class))).thenReturn(true);

            boolean result = vitaldatenService.updateVitaldaten(patientId, vitaldaten);
            
            assertTrue(result);
            verify(vitaldatenRepository).updateVitaldaten(patientId, vitaldaten);

        }

        @Test
        void shouldThrowExceptionWhenUpdatingNonExistentVitaldaten() {
            when(vitaldatenRepository.getVitaldatenById(any(UUID.class))).thenReturn(Optional.empty());
            assertThrows(VitaldatenNotFoundException.class, () -> 
                vitaldatenService.updateVitaldaten(patientId, vitaldaten));
        }
    }

    @Nested
    class DeleteVitaldatenTests {
        @Test
        void shouldDeleteVitaldatenSuccessfully() {
            when(vitaldatenRepository.getVitaldatenById(any(UUID.class))).thenReturn(Optional.of(vitaldaten));
            assertDoesNotThrow(() -> vitaldatenService.deleteVitaldaten(patientId, vitaldaten));
            verify(vitaldatenRepository).deleteVitaldaten(patientId, vitaldaten);
        }

        @Test
        void shouldThrowExceptionWhenDeletingNonExistentVitaldaten() {
            when(vitaldatenRepository.getVitaldatenById(any(UUID.class))).thenReturn(Optional.empty());
            assertThrows(VitaldatenNotFoundException.class, () -> 
                vitaldatenService.deleteVitaldaten(patientId, vitaldaten));
        }
    }
}