package com.bht.meditrack.patientenverwaltung.infrastructure.persistence;

import com.bht.meditrack.vitaldatenmanagement.infrastructure.persistence.VitaldatenEntity;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import com.bht.meditrack.patientenverwaltung.domain.valueojects.Krankenkasse;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "patient")
@Getter
@Setter
public class PatientEntity {

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
    private List<VitaldatenEntity> vitaldaten;

}
