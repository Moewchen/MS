package com.bht.MediTrack.Patientenverwaltung.domain.model;
import com.bht.MediTrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.lang.Nullable;



@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Nullable
    @Embedded
    private Krankenkasse krankenkasse;
    private String krankenversicherungsnummer;
    @Embedded
    private Personendaten personendaten;
    @Embedded
    private Kontaktdaten kontaktdaten;
    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Vitaldaten> vitaldaten;

    /*
    public Patient(UUID patientId, Krankenkasse krankenkasse, String krankenversicherungsnummer, Personendaten personendaten, Kontaktdaten kontaktdaten, Adresse adresse) {
        this.id = patientId;
        this.krankenkasse = krankenkasse;
        this.krankenversicherungsnummer = krankenversicherungsnummer;
        this.personendaten = personendaten;
        this.kontaktdaten = kontaktdaten;
        this.adresse = adresse;
    }

     */

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    /*
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

     */
}
