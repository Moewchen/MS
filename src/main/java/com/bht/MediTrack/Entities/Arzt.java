package com.bht.MediTrack.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "arzt")
public class Arzt extends Nutzer{

    @Id
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
    public String getFachrichtung() {
        return fachrichtung;
    }

    public void setArztId(UUID ArztId) {
        this.id = ArztId;
    }
    public void setFachrichtung(String fachrichtung) {
        this.fachrichtung = fachrichtung;
    }
}