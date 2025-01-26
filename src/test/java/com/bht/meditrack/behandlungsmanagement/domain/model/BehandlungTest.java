package com.bht.meditrack.behandlungsmanagement.domain.model;

import com.bht.meditrack.arztverwaltung.domain.model.Arzt;
import com.bht.meditrack.patientenverwaltung.domain.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

 class BehandlungTest {

    private Behandlung behandlung;
    private Patient patient;
    private Arzt arzt;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setId(UUID.randomUUID());
        arzt = new Arzt();
        arzt.setId(UUID.randomUUID());
        behandlung = new Behandlung(UUID.randomUUID(), "Initial Beschreibung", patient, arzt);
    }
    @Test
     void testSetAndGetBeschreibung() {
        behandlung.setBeschreibung("Neue Beschreibung");
        assertEquals("Neue Beschreibung", behandlung.getBeschreibung(), "Die Beschreibung sollte korrekt gesetzt werden.");
    }
    @Test
     void testBehandlungWithNullArzt() {
        Behandlung nullArztBehandlung = new Behandlung(UUID.randomUUID(), "Emergency", patient, null);
        assertNull(nullArztBehandlung.getArzt(), "Arzt should be null in case of invalid data.");
    }
    @Test
     void testGetPatientId() {
        assertEquals(patient.getId(), behandlung.getPatientId(), "Die Patienten-ID sollte korrekt abgerufen werden.");
    }
    @Test
     void testSetAndGetArzt() {
        Arzt neuerArzt = new Arzt();
        neuerArzt.setId(UUID.randomUUID());
        behandlung.setArzt(neuerArzt);
        assertEquals(neuerArzt, behandlung.getArzt(), "Der Arzt sollte korrekt gesetzt und abgerufen werden.");
    }
}