package com.bht.MediTrack.Services;

import com.bht.MediTrack.Entities.Vitaldaten;
import com.bht.MediTrack.Repositories.VitaldatenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
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
        vitaldaten.setId(patientId);
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

        when(vitaldatenRepository.getVitaldatenByID(patientId)).thenReturn(Optional.of(vitaldaten));

        Optional<Vitaldaten> result = vitaldatenService.getVitaldatenById(patientId);

        assertTrue(result.isPresent());
        assertEquals(vitaldaten, result.get());
    }

    @Test
    void testUpdateVitaldaten() {
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

        when(vitaldatenRepository.getVitaldatenByID(any(UUID.class)))
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
}