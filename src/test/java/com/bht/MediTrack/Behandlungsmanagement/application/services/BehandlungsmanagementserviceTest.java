package com.bht.MediTrack.Behandlungsmanagement.application.services;

import com.bht.MediTrack.Behandlungsmanagement.domain.model.Behandlung;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.PublisherEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BehandlungsmanagementserviceTest {

    private Behandlungsmanagementservice service;
    private Patient patient;
    private Behandlung behandlung;
    private PublisherEvent eventListener;
    private ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    public void setUp() {
        applicationEventPublisher = mock(ApplicationEventPublisher.class);
        eventListener = new PublisherEvent(applicationEventPublisher);
        service = new Behandlungsmanagementservice(eventListener);
        patient = new Patient();
        patient.setId(UUID.randomUUID());
        behandlung = new Behandlung(UUID.randomUUID(), "Initial Beschreibung", patient, null);

    }
    @Test
    public void testCreateBehandlung() {
        // Testet das Erstellen und Speichern einer neuen Behandlung
        Behandlung createdBehandlung = service.createBehandlung(behandlung);
        assertNotNull(createdBehandlung.getId(), "ID sollte nicht null sein.");
        assertEquals("Initial Beschreibung", createdBehandlung.getBeschreibung(), "Beschreibung sollte übereinstimmen.");
    }
    @Test
    public void testGetBehandlungenByPatientId() {
        //Fügt zwei Behandlungen für denselben Patienten hinzu
        service.createBehandlung(behandlung);
        Behandlung behandlung2 = new Behandlung(UUID.randomUUID(), "Beschreibung 2", patient, null);
        service.createBehandlung(behandlung2);
        List<Behandlung> patientBehandlungen = service.getBehandlungenByPatientId(patient.getId());
        assertEquals(2, patientBehandlungen.size(), "Es sollten zwei Behandlungen für diesen Patienten vorhanden sein.");
    }
    @Test
    public void testUpdateBehandlung() {
        //Fügt die Behandlung hinzu und aktualisiert die Beschreibung
        service.createBehandlung(behandlung);
        String neueBeschreibung = "Aktualisierte Beschreibung";
        service.updateBehandlung(neueBeschreibung, behandlung.getId());
        Behandlung updatedBehandlung = service.getBehandlungenByPatientId(patient.getId()).get(0);
        assertEquals(neueBeschreibung, updatedBehandlung.getBeschreibung(), "Beschreibung sollte aktualisiert sein.");
    }
}