package com.bht.meditrack.arztverwaltung.domain.events;

import com.bht.meditrack.arztverwaltung.domain.valueojects.Fachrichtung;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ArztAngelegtEventTest {

    private UUID arztId;
    private Fachrichtung fachrichtung;
    private Personendaten personendaten;
    private Kontaktdaten kontaktdaten;
    private Adresse adresse;
    private ArztAngelegtEvent arztAngelegtEvent;

    @BeforeEach
    void setUp() {
        // Set up the test data
        arztId = UUID.randomUUID();
        fachrichtung = new Fachrichtung("Allgemeinmedizin");
        personendaten = new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1980,1,1));
        kontaktdaten = new Kontaktdaten("max.mustermann@mail.de", "0123456789");
        adresse = new Adresse("Musterstraße", "1","12345", "Musterstadt");

        // Erstelle ein ArztAngelegtEvent
        arztAngelegtEvent = new ArztAngelegtEvent(arztId, fachrichtung, personendaten, kontaktdaten, adresse);
    }

    @Test
    void testArztId() {
        assertEquals(arztId, arztAngelegtEvent.getArztId(), "Die Arzt-ID sollte korrekt sein.");
    }

    @Test
    void testArztFachrichtung() {
        assertEquals(fachrichtung, arztAngelegtEvent.getFachrichtung(), "Die Fachrichtung sollte korrekt sein.");
    }

    @Test
    void testArztPersonendaten() {
        assertEquals(personendaten, arztAngelegtEvent.getPersonendaten(), "Die Personendaten sollten korrekt sein.");
    }

    @Test
    void testArztKontaktdaten() {
        assertEquals(kontaktdaten, arztAngelegtEvent.getKontaktdaten(), "Die Kontaktdaten sollten korrekt sein.");
    }

    @Test
    void testArztAdresse() {
        assertEquals(adresse, arztAngelegtEvent.getAdresse(), "Die Adresse sollte korrekt sein.");
    }
}
