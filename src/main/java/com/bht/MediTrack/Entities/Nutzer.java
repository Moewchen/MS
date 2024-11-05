package com.bht.MediTrack.Entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "user")
public class Nutzer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName, lastName, titel;
    private LocalDate dateOfBirth;
    private String email, telefon;
    @Embedded
    private Adresse adresse;


    public Nutzer(String firstName, String lastName, String titel, LocalDate dateOfBirth, String email, String telefon, Adresse adresse) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.titel = titel;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.telefon = telefon;
        this.adresse = adresse;
    }

    public Nutzer() {

    }

    public Adresse getAddress() {
        return adresse;
    }

    public void setAddress(Adresse address) {
        this.adresse = address;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(@Nullable LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    public void setTelefon(@Nullable String telefon) {
        this.telefon = telefon;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitel() {
        return titel;
    }

    @Nullable
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    @Nullable
    public String getTelefon() {
        return telefon;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public UUID getId() {
        return id;
    }

    // toString method for easy debugging
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

    // equals and hashCode based on id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nutzer nutzer = (Nutzer) o;
        return id != null && id.equals(nutzer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


