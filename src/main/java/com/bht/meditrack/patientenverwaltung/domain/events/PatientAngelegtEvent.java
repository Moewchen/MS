package com.bht.meditrack.patientenverwaltung.domain.events;
import com.bht.meditrack.patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import java.time.Instant;
import java.util.UUID;


public class PatientAngelegtEvent {
    private final UUID patientId;
    private final Krankenkasse krankenkasse;
    private final String krankenversicherungsnummer;
    private final Personendaten personendaten;
    private final Kontaktdaten kontaktdaten;
    private final Adresse adresse;
    private final Instant createdAt;

    public PatientAngelegtEvent(UUID patientId,
            Krankenkasse krankenkasse,
            String krankenversicherungsnummer,
            Personendaten personendaten,
            Kontaktdaten kontaktdaten,
            Adresse adresse) {
        this.patientId = patientId;
        this.krankenkasse = krankenkasse;
        this.krankenversicherungsnummer = krankenversicherungsnummer;
        this.personendaten = personendaten;
        this.kontaktdaten = kontaktdaten;
        this.adresse = adresse;
        this.createdAt = Instant.now();
    }

    public UUID getPatientId() {
        return patientId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Krankenkasse getKrankenkasse() {
        return krankenkasse;
    }

    public String getKrankenversicherungsnummer() {
        return krankenversicherungsnummer;
    }

    public Personendaten getPersonendaten() {
        return personendaten;
    }

    public Kontaktdaten getKontaktdaten() {
        return kontaktdaten;
    }

    public Adresse getAdresse() {
        return adresse;
    }
}
