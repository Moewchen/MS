package com.bht.MediTrack.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class BehandlungTest {

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
    public void testSetAndGetBeschreibung() {
        behandlung.setBeschreibung("Neue Beschreibung");
        assertEquals("Neue Beschreibung", behandlung.getBeschreibung(), "Die Beschreibung sollte korrekt gesetzt werden.");
    }

    @Test
    public void testGetPatientId() {
        assertEquals(patient.getId(), behandlung.getPatientId(), "Die Patienten-ID sollte korrekt abgerufen werden.");
    }

    @Test
    public void testSetAndGetArzt() {
        Arzt neuerArzt = new Arzt();
        neuerArzt.setId(UUID.randomUUID());

        behandlung.setArzt(neuerArzt);
        assertEquals(neuerArzt, behandlung.getArzt(), "Der Arzt sollte korrekt gesetzt und abgerufen werden.");
    }
}