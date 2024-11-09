package com.bht.MediTrack.Entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Nutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName, lastName, titel;
    private LocalDate dateOfBirth;
    private String email, telefon;
    private String adresse;

    public Nutzer(String firstName, String lastName, String titel, LocalDate dateOfBirth, String telefon, String email,
            String adresse) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.titel = titel;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.telefon = telefon;
        this.adresse = adresse;
    }

    public Nutzer() {}

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getTitel() {
        return titel;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Nutzer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", titel='" + titel + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", adresse=" + adresse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Nutzer nutzer = (Nutzer) o;
        return Objects.equals(id, nutzer.id) && Objects.equals(firstName, nutzer.firstName) && Objects.equals(
                lastName, nutzer.lastName) && Objects.equals(titel, nutzer.titel) && Objects.equals(dateOfBirth,
                nutzer.dateOfBirth) && Objects.equals(email, nutzer.email) && Objects.equals(telefon, nutzer.telefon)
                && Objects.equals(adresse, nutzer.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, titel, dateOfBirth, email, telefon, adresse);
    }
}


