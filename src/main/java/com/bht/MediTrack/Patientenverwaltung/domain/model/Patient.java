package com.bht.MediTrack.Patientenverwaltung.domain.model;
import com.bht.MediTrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Nullable
    private Krankenkasse krankenkasse;
    private String krankenversicherungsnummer;
    private Personendaten personendaten;
    private Kontaktdaten kontaktdaten;
    private Adresse adresse;

    public Patient() {}

    public Patient(UUID patientId, Krankenkasse krankenkasse, String krankenversicherungsnummer, Personendaten personendaten, Kontaktdaten kontaktdaten, Adresse adresse) {
        this.id = patientId;
        this.krankenkasse = krankenkasse;
        this.krankenversicherungsnummer = krankenversicherungsnummer;
        this.personendaten = personendaten;
        this.kontaktdaten = kontaktdaten;
        this.adresse = adresse;
    }

    public UUID getId() {
        return id;
    }
    public Krankenkasse getKrankenkasse() {
        return krankenkasse;
    }
    public String getKrankenversicherungsnummer() {return krankenversicherungsnummer;}
    public Personendaten getPersonendaten() {return personendaten;}
    public Kontaktdaten getKontaktdaten() {return kontaktdaten;}
    public Adresse getAdresse() {return adresse;}

    public void setId(UUID id) {
        this.id = id;
    }
    public void setKrankenkasse(Krankenkasse krankenkasse) {
        this.krankenkasse = krankenkasse;
    }
    public void setKrankenversicherungsnummer(String krankenversicherungsnummer) {
        this.krankenversicherungsnummer = krankenversicherungsnummer;
    }
    public void setPersonendaten(Personendaten personendaten) {
        this.personendaten = personendaten;
    }
    public void setKontaktdaten(Kontaktdaten kontaktdaten) {
        this.kontaktdaten = kontaktdaten;
    }
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
