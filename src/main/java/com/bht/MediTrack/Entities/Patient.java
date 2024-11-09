package com.bht.MediTrack.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;

public class Patient extends Nutzer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String krankenkasse;
    private String krankenversicherungsnummer;

    public Patient(String firstName, String lastName, String titel, LocalDate dateOfBirth, String telefon, String email,
            String adresse, String krankenkasse, String krankenversicherungsnummer) {
        super(firstName, lastName, titel, dateOfBirth, telefon, email, adresse);
        this.krankenkasse = krankenkasse;
        this.krankenversicherungsnummer = krankenversicherungsnummer;
    }

    public Patient() {}

    @Override
    public UUID getId() {
        return id;
    }

    public String getKrankenkasse() {
        return krankenkasse;
    }

    public String getKrankenversicherungsnummer() {
        return krankenversicherungsnummer;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public void setKrankenkasse(String krankenkasse) {
        this.krankenkasse = krankenkasse;
    }

    public void setKrankenversicherungsnummer(String krankenversicherungsnummer) {
        this.krankenversicherungsnummer = krankenversicherungsnummer;
    }
}
