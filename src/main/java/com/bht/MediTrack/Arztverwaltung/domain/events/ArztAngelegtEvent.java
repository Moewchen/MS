package com.bht.MediTrack.Arztverwaltung.domain.events;

import com.bht.MediTrack.Arztverwaltung.domain.valueojects.Fachrichtung;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;

import java.time.Instant;
import java.util.UUID;

public class ArztAngelegtEvent {
    private final UUID arztId;
    private final Fachrichtung fachrichtung;
    private final Personendaten personendaten;
    private final Kontaktdaten kontaktdaten;
    private final Adresse adresse;
    private final Instant createdAt;

    public ArztAngelegtEvent(UUID arztId, Fachrichtung fachrichtung, Personendaten personendaten, Kontaktdaten kontaktdaten, Adresse adresse) {
        this.arztId = arztId;
        this.fachrichtung = fachrichtung;
        this.personendaten = personendaten;
        this.kontaktdaten = kontaktdaten;
        this.adresse = adresse;
        this.createdAt = Instant.now();
    }

    public UUID getArztId() {
        return arztId;
    }

    public Instant getArztCreatedAt() {
        return createdAt;
    }

    public Fachrichtung getArztFachrichtung() {
        return fachrichtung;
    }

    public Personendaten getArztPersonendaten() {
        return personendaten;
    }

    public Kontaktdaten getArztKontaktdaten() {
        return kontaktdaten;
    }

    public Adresse getArztAdresse() {
        return adresse;
    }

}

