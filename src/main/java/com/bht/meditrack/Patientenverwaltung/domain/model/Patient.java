package com.bht.meditrack.Patientenverwaltung.domain.model;
import com.bht.meditrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.meditrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.lang.Nullable;


@EntityScan
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


    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
