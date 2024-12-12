package com.bht.MediTrack.Arztverwaltung.domain.events;

import com.bht.MediTrack.Arztverwaltung.domain.valueojects.Fachrichtung;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
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
        assertEquals(fachrichtung, arztAngelegtEvent.getArztFachrichtung(), "Die Fachrichtung sollte korrekt sein.");
    }

    @Test
    void testArztPersonendaten() {
        assertEquals(personendaten, arztAngelegtEvent.getArztPersonendaten(), "Die Personendaten sollten korrekt sein.");
    }

    @Test
    void testArztKontaktdaten() {
        assertEquals(kontaktdaten, arztAngelegtEvent.getArztKontaktdaten(), "Die Kontaktdaten sollten korrekt sein.");
    }

    @Test
    void testArztAdresse() {
        assertEquals(adresse, arztAngelegtEvent.getArztAdresse(), "Die Adresse sollte korrekt sein.");
    }

    @Test
    void testArztCreatedAt() {
        Instant createdAt = arztAngelegtEvent.getArztCreatedAt();
        Instant now = Instant.now();

        assertTrue(createdAt.isBefore(now) && createdAt.isAfter(now.minusMillis(200)),
                "createdAt sollte innerhalb der letzten 100 Millisekunden liegen.");
    }
}
