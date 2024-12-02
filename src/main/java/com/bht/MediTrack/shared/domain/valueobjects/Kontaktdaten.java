package com.bht.MediTrack.shared.domain.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Kontaktdaten (String email, String telefon){

    //Konstruktor mit Prüfung
    public Kontaktdaten{
        //TODO spaeter umschreiben
        if (!isValidEmail(email)){
            throw new IllegalArgumentException("Ungültige E-Mail-Adresse: " + email);
        }
        if (!isValidTelefon(telefon)){
            throw new IllegalArgumentException("Ungültige Telefonnummer: " + telefon);
        }
    }

    //Prüfung E-Mail
    private static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");//gültiges E-Mail-Format
    }
    //Prüfung Telefonnummer
    private static boolean isValidTelefon(String telefon) {
        if (telefon == null) return false;
        return telefon.matches("^\\+\\d{1,3}\\d{4,14}(?:x.+)?$");//gültiges Telefon-Format
    }

    @Override
    public String toString() {
        return "Kontaktdaten{" +
                ", email='" + email + '\'' +
                ", telefon='" + telefon +
                '}';
    }
}

