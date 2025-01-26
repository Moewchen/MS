package com.bht.meditrack.behandlungsmanagement.application.services;

import com.bht.meditrack.behandlungsmanagement.domain.events.BehandlungErstelltEvent;
import com.bht.meditrack.behandlungsmanagement.domain.model.Behandlung;
import com.bht.meditrack.behandlungsmanagement.infrastructure.repositories.BehandlungRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BehandlungsmanagementserviceTest {

    @Mock
    private BehandlungRepository behandlungRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private Behandlungsmanagementservice service;

    private Behandlung behandlung;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        id = UUID.randomUUID();

        behandlung = new Behandlung();
        behandlung.setId(UUID.randomUUID());
        behandlung.setBeschreibung("Initial Beschreibung");
        behandlung.setPatient(id);
    }

    @Test
    void testCreateBehandlung() {
        // Mocking des Repository-Verhaltens
        when(behandlungRepository.save(any(Behandlung.class))).thenReturn(behandlung);

        // Teste das Erstellen und Speichern einer neuen Behandlung
        Behandlung result = service.createBehandlung(behandlung);

        // Assertions
        assertNotNull(result.getId(), "ID sollte nicht null sein.");
        assertEquals("Initial Beschreibung", result.getBeschreibung(), "Beschreibung sollte übereinstimmen.");
        assertEquals(id, result.getPatient(), "Patient-ID sollte übereinstimmen.");

        // Verify, dass das Event veröffentlicht wurde
        verify(eventPublisher, times(1)).publishEvent(any(BehandlungErstelltEvent.class));
    }

    @Test
    void testGetBehandlungenByPatientId() {
        // Mocking der Rückgabe von Behandlungen
        Behandlung behandlung2 = new Behandlung();
        behandlung2.setId(UUID.randomUUID());
        behandlung2.setBeschreibung("Beschreibung 2");
        behandlung2.setPatient(id);

        List<Behandlung> behandlungen = new ArrayList<>();
        behandlungen.add(behandlung);
        behandlungen.add(behandlung2);

        when(behandlungRepository.getBehandlungenByPatientId(id)).thenReturn(behandlungen);

        // Teste das Abrufen von Behandlungen
        List<Behandlung> result = service.getBehandlungenByPatientId(id);

        // Assertions
        assertEquals(2, result.size(), "Es sollten zwei Behandlungen für diesen Patienten vorhanden sein.");
        assertTrue(result.contains(behandlung), "Behandlung 1 sollte enthalten sein.");
        assertTrue(result.contains(behandlung2), "Behandlung 2 sollte enthalten sein.");
    }
}
