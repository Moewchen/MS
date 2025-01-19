package com.bht.meditrack.Patientenverwaltung.domain.model;

import com.bht.meditrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.meditrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
import java.util.UUID;

@EntityScan
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
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

    public Patient(UUID id, Krankenkasse krankenkasse, String krankenversicherungsnummer, Personendaten personendaten, Kontaktdaten kontaktdaten, Adresse adresse) {
        this.id = id;
        this.krankenkasse = krankenkasse;
        this.krankenversicherungsnummer = krankenversicherungsnummer;
        this.personendaten = personendaten;
        this.kontaktdaten = kontaktdaten;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", krankenkasse=" + krankenkasse +
                ", krankenversicherungsnummer='" + krankenversicherungsnummer + '\'' +
                ", personendaten=" + personendaten +
                ", kontaktdaten=" + kontaktdaten +
                ", adresse=" + adresse +
                '}';
    }
}
