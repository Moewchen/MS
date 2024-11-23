package com.bht.MediTrack.shared.domain.valueobjects;

import java.time.LocalDate;

public record Personendaten(String firstName, String lastName, String titel, LocalDate dateOfBirth) {

    //Konstruktor mit Prüfung
    public Personendaten {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Vorname darf nicht leer sein");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nachname darf nicht leer sein");
        }
        if (titel == null || titel.trim().isEmpty()) {
            throw new IllegalArgumentException("Titel draf nicht leer sein");
        }
        if (isValidBirthday(dateOfBirth)) {
            throw new IllegalArgumentException("Geburtsdatum darf nicht in der Zukunft liegen");
        }
    }

    //Prüfung Geburtstag
    //ToDo: Prüfung Geburtstag vervollständigen
    private static boolean isValidBirthday(LocalDate dateOfBirth) {
        if (dateOfBirth == null) return false;
        return dateOfBirth.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Personendaten{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", titel='" + titel + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}

