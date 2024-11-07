package com.bht.MediTrack.Entities;
import jakarta.persistence.Embeddable;

@Embeddable
public class Adresse {
    static String strasse;
    static String hausnummer;
    private static String plz;
    static String stadt;
    static String land;

    public Adresse(String strasse, String hausnummer, String plz, String stadt, String land) {
        Adresse.strasse = strasse;
        Adresse.hausnummer = hausnummer;
        Adresse.plz = plz;
        Adresse.stadt = stadt;
        Adresse.land = land;
    }

    public Adresse() {

    }

    public static String getStrasse() {
        return strasse;
    }

    public static String getHausnummer() {
        return hausnummer;
    }

    public static String getPlz() {
        return plz;
    }

    public static String getStadt() {
        return stadt;
    }

    public static String getLand() {
        return land;
    }

    public static void setStrasse(String strasse) {
        Adresse.strasse = strasse;
    }

    public static void setHausnummer(String hausnummer) {
        Adresse.hausnummer = hausnummer;
    }

    public static void setPlz(String plz) {
        Adresse.plz = plz;
    }

    public static void setStadt(String stadt) {
        Adresse.stadt = stadt;
    }

    public static void setLand(String land) {
        Adresse.land = land;
    }


    @Override
    public String toString() {
        return "Adresse{" +
                "strasse='" + strasse + '\'' +
                ", hausnummer='" + hausnummer + '\'' +
                ", plz='" + plz + '\'' +
                ", stadt='" + stadt + '\'' +
                ", land='" + land + '\'' +
                '}';
    }
}
