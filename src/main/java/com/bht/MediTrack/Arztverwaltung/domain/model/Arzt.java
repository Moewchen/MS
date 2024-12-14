package com.bht.MediTrack.Arztverwaltung.domain.model;

import com.bht.MediTrack.Arztverwaltung.domain.valueojects.Fachrichtung;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Arzt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Embedded
    private Fachrichtung fachrichtung;
    private Personendaten personendaten;
    private Kontaktdaten kontaktdaten;
    private Adresse adresse;

    public Arzt(Fachrichtung fachrichtung, Personendaten personendaten, Kontaktdaten kontaktdaten, Adresse adresse) {
        this.fachrichtung = fachrichtung;
        this.personendaten = personendaten;
        this.kontaktdaten = kontaktdaten;
        this.adresse = adresse;
    }

    public Arzt() {}

    public UUID getId(){
        return id;
    }
    public Fachrichtung getFachrichtung() {
        return fachrichtung;
    }
    public Personendaten getPersonendaten() {return personendaten;}
    public Kontaktdaten getKontaktdaten() {return kontaktdaten;}
    public Adresse getAdresse() {return adresse;}

    public void setId(UUID id) {
        this.id = id;
    }
    public void setFachrichtung(Fachrichtung fachrichtung){this.fachrichtung = fachrichtung;}
    public void setKontaktdaten(Kontaktdaten kontaktdaten){this.kontaktdaten = kontaktdaten;}
    public void setPersonendaten(Personendaten personendaten){this.personendaten = personendaten;}
    public void setAdresse(Adresse adresse){this.adresse = adresse;}
}



