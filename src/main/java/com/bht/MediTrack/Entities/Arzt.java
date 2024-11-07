package com.bht.MediTrack.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
public class Arzt extends Nutzer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String fachrichtung;

    public Arzt(String fachrichtung, String firstName, String lastName, String titel, LocalDate dateOfBirth, String email, String telefon, Adresse adresse){
        super(firstName, lastName, titel, dateOfBirth, email, telefon, adresse);
        this.fachrichtung = fachrichtung;
    }

    public Arzt() {
    }

    public UUID getArztId(){
        return id;
    }

    public void setArztId(UUID ArztId) {
        this.id = ArztId;
    }
    public String getFachrichtung() {
        return fachrichtung;
    }
    public void setFachrichtung(String fachrichtung) {
        this.fachrichtung = fachrichtung;
    }
}
