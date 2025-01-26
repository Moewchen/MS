package com.bht.meditrack.patientenverwaltung.domain.model;
import com.bht.meditrack.patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.meditrack.vitaldatenmanagement.infrastructure.persistence.VitaldatenEntity;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;

import java.util.List;
import java.util.UUID;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Patient {

    private UUID id;
    private Krankenkasse krankenkasse;
    private String krankenversicherungsnummer;
    private Personendaten personendaten;
    private Kontaktdaten kontaktdaten;
    private Adresse adresse;
    private List<VitaldatenEntity> vitaldaten;

    public Patient(UUID id, Krankenkasse krankenkasse, String krankenversicherungsnummer, Personendaten personendaten, Kontaktdaten kontaktdaten, Adresse adresse, List<VitaldatenEntity> vitaldaten) {
        this.id = id;
        this.krankenkasse = krankenkasse;
        this.krankenversicherungsnummer = krankenversicherungsnummer;
        this.personendaten = personendaten;
        this.kontaktdaten = kontaktdaten;
        this.adresse = adresse;
        this.vitaldaten = vitaldaten;
    }
}
