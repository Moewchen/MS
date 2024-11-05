package com.bht.MediTrack.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BehandlungTest {

    private Patient patient;
    private Arzt arzt;
    private Behandlung behandlung;

    @BeforeEach
    //TODO: Anpassungen notwendig
    void setUp() {
        // Beispiel-Patient und Arzt erstellen
        patient = new Patient();
        arzt = new Arzt();

        // Behandlung erstellen
        behandlung = new Behandlung();
    }

    @Test
    void testBehandlungErstellen() {
        // Überprüft, ob die Behandlung korrekt erstellt wurde
        assertNotNull(behandlung);
        assertEquals("B1", behandlung.getId());
        assertEquals("Routineuntersuchung", behandlung.getBeschreibung());
        assertEquals(patient, behandlung.getPatient());
        assertEquals(arzt, behandlung.getArzt());
    }

    @Test
    void testBehandlungBeschreibungAktualisieren() {
        // Beschreibung der Behandlung aktualisieren
        behandlung.setBeschreibung("Nachuntersuchung");
        assertEquals("Nachuntersuchung", behandlung.getBeschreibung());
    }

    @Test
    void testPatientZuordnung() {
        // Überprüfen, ob der Patient korrekt zugeordnet ist
        assertEquals("Max", behandlung.getPatient().getFirstName());
        assertEquals("Mustermann", behandlung.getPatient().getLastName());
    }

    @Test
    void testArztZuordnung() {
        // Überprüfen, ob der Arzt korrekt zugeordnet ist
        assertEquals("Dr. Schmidt", behandlung.getArzt().getFirstName());
        assertEquals("Allgemeinmedizin", behandlung.getArzt().getFachrichtung());
    }

    @Test
    void testNullValues() {
        // Überprüfen, ob Null-Werte korrekt behandelt werden
        Behandlung nullBehandlung = new Behandlung(null, null, null, null);
        assertNull(nullBehandlung.getId());
        assertNull(nullBehandlung.getBeschreibung());
        assertNull(nullBehandlung.getPatient());
        assertNull(nullBehandlung.getArzt());
    }
}