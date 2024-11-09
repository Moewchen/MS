package com.bht.MediTrack.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

public class Arzt extends Nutzer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String fachrichtung;

    public Arzt(String fachrichtung, String firstName, String lastName, String titel, LocalDate dateOfBirth, String telefon, String email, String adresse){
        super(firstName, lastName, titel, dateOfBirth, email, telefon, adresse);
        this.fachrichtung = fachrichtung;
    }

    public Arzt() {}

    public UUID getId(){
        return id;
    }
    public String getFachrichtung() {
        return fachrichtung;
    }

    public void setId(UUID ArztId) {
        this.id = ArztId;
    }
    public void setFachrichtung(String fachrichtung) {
        this.fachrichtung = fachrichtung;
    }
}
