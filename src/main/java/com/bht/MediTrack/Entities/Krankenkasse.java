package com.bht.MediTrack.Entities;
import jakarta.persistence.Embeddable;


@Embeddable
public class Krankenkasse {
    private String krankenkasseName;
    private String krankenversicherungsnummer;

    public Krankenkasse(String krankenkasseName,
            String krankenversicherungsnummer) {
        this.krankenkasseName = krankenkasseName;
        this.krankenversicherungsnummer = krankenversicherungsnummer;
    }

    public Krankenkasse() {}

    public String getKrankenkasseName() {
        return krankenkasseName;
    }

    public String getKrankenversicherungsnummer() {
        return krankenversicherungsnummer;
    }
    public void setKrankenkasseName(String krankenkasseName) {
        this.krankenkasseName = krankenkasseName;
    }

    public void setKrankenversicherungsnummer(String krankenversicherungsnummer) {
        this.krankenversicherungsnummer = krankenversicherungsnummer;
    }

    @Override
    public String toString() {
        return "Krankenkasse{krankenkasseName='" + krankenkasseName + "', krankenversicherungsnummer='" + krankenversicherungsnummer + "'}";
    }
}
