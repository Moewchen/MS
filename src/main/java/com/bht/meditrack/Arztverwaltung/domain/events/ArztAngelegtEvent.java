package com.bht.meditrack.Arztverwaltung.domain.events;

import com.bht.meditrack.Arztverwaltung.domain.valueojects.Fachrichtung;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
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

}

