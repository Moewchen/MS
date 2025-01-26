package com.bht.meditrack.shared.domain.valueobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record Adresse (String strasse, String hausnummer, String plz, String ort){

    //Konstruktor mit Prüfung
    public Adresse {
        if (strasse == null || strasse.trim().isEmpty()){
            throw new IllegalArgumentException("Straße darf nicht leer sein");
        }

        if (hausnummer == null || hausnummer.trim().isEmpty()){
            throw new IllegalArgumentException("Hausnummer darf nicht leer sein");
        }
        if (!isValidPLZ(plz)){
            throw new IllegalArgumentException("Ungültige PLZ: " + plz);
        }

        if (ort == null || ort.trim().isEmpty()){
            throw new IllegalArgumentException("Ort darf nicht leer sein");
        }
    }

    private static boolean isValidPLZ(String plz){
        if (plz == null) return false;
        return plz.matches("\\d{5}"); //5 Ziffern
    }


    @Override
    public String toString() {
        return "Adresse{" +
                "strasse='" + strasse + '\'' +
                ", hausnummer='" + hausnummer + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }
}
