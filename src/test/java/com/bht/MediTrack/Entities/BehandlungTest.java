package com.bht.MediTrack.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BehandlungTest {

    private Patient patient;
    private Arzt arzt;
    private Behandlung behandlung;

    @BeforeEach
    void setUp() {
        // Beispiel-Patient und Arzt erstellen
        patient = new Patient("1", "Max", "Mustermann", "Dr.", "1985-07-10", "max@mustermann.de", "0123456789", "Musterstraße 1", "AOK");
        arzt = new Arzt("1", "Dr. Schmidt", "Allgemeinmedizin");

        // Behandlung erstellen
        behandlung = new Behandlung("B1", "Routineuntersuchung", patient, arzt);
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
        assertEquals("Max", behandlung.getPatient().getVorname());
        assertEquals("Mustermann", behandlung.getPatient().getName());
    }

    @Test
    void testArztZuordnung() {
        // Überprüfen, ob der Arzt korrekt zugeordnet ist
        assertEquals("Dr. Schmidt", behandlung.getArzt().getName());
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