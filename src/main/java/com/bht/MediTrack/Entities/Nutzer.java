package com.bht.MediTrack.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "user")
public class User {
    @Id
    private UUID id;
    private String firstName;
    @Setter
    private String lastName;
    private String titel;
    @Nullable
    private LocalDate dateOfBirth;
    @Nullable
    private String email;
    @Nullable
    private String telefon;
    private Adresse adresse;

    public User(
            UUID id,
            String firstName,
            String lastName,
            String titel,
            @Nullable
            LocalDate dateOfBirth,
            @Nullable
            String email,
            @Nullable
            String telofo,
            Adresse adresse) {
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.titel = titel;
        this.email = email;
        this.telefon = telofo;
        this.adresse = adresse;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Adresse getAddress() {
        return adresse;
    }

    public void setAddress(Adresse address) {
        this.adresse = address;
    }
}

public class Adresse {
    private static String plz;
    static String ort;
    @Nullable
    static String land;
    static String bundesland;
    static String strasse;
    static String hausnummer;
}
