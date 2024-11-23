package com.bht.MediTrack.shared.domain.valueobjects;

public record Kontaktdaten (String email, String telefon){

    //Konstruktor mit Prüfung
    public Kontaktdaten{
        if (isValidEmail(email)){
            throw new IllegalArgumentException("Ungültige E-Mail-Adresse: " + email);
        }
        if (isValidTelefon(telefon)){
            throw new IllegalArgumentException("Ungültige Telefonnummer: " + telefon);
        }
    }

    //Prüfung E-Mail
    //ToDo: Prüfung E-Mail vervollständigen
    private static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.contains("@");
    }
    //Prüfung Telefonnummer
    //ToDo: Prüfung Telefon vervollständigen
    private static boolean isValidTelefon(String telefon) {
        if (telefon == null) return false;
        return telefon.contains("+");
    }

    @Override
    public String toString() {
        return "Kontaktdaten{" +
                ", email='" + email + '\'' +
                ", telefon='" + telefon +
                '}';
    }
}

