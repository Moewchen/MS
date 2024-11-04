package com.bht.MediTrack.Entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "patient")
public class Patient extends Nutzer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Embedded
    private Krankenkasse krankenkasse;


    public Patient(String firstName, String lastName, String titel, LocalDate dateOfBirth, String email,
            String telefon, Adresse adresse, Krankenkasse krankenkasse) {
        super(firstName, lastName, titel, dateOfBirth, email, telefon, adresse);
        this.krankenkasse = krankenkasse;
    }

    public Patient() {
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Krankenkasse getKrankenkasse() {
        return krankenkasse;
    }
    public void setKrankenkasse(Krankenkasse krankenkasse) {
        this.krankenkasse = krankenkasse;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + this.id +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", krankenkasse=" + getKrankenkasse() +
                '}';
    }
}


