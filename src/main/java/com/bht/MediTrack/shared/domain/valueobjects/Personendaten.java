package com.bht.MediTrack.shared.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public record Personendaten(String firstName, String lastName, String titel,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate dateOfBirth) {

    public Personendaten {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Vorname darf nicht leer sein");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nachname darf nicht leer sein");
        }
        if (titel == null || titel.trim().isEmpty()) {
            throw new IllegalArgumentException("Titel darf nicht leer sein");
        }
        if (!isValidBirthday(dateOfBirth)) {
            throw new IllegalArgumentException("Geburtsdatum darf nicht in der Zukunft liegen");
        }
    }

    private static boolean isValidBirthday(LocalDate dateOfBirth) {
        return dateOfBirth != null && dateOfBirth.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Personendaten{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", titel='" + titel + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}



//package com.bht.MediTrack.shared.domain.valueobjects;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.Embeddable;
//import java.time.LocalDate;
//
//@Embeddable
//public record Personendaten(String firstName, String lastName, String titel,
//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate dateOfBirth) {
//
//    public Personendaten {
//        if (firstName == null || firstName.trim().isEmpty()) {
//            throw new IllegalArgumentException("Vorname darf nicht leer sein");
//        }
//        if (lastName == null || lastName.trim().isEmpty()) {
//            throw new IllegalArgumentException("Nachname darf nicht leer sein");
//        }
//        if (titel == null || titel.trim().isEmpty()) {
//            throw new IllegalArgumentException("Titel darf nicht leer sein");
//        }
//        if (!isValidBirthday(dateOfBirth)) {
//            throw new IllegalArgumentException("Geburtsdatum darf nicht in der Zukunft liegen");
//        }
//    }
//
//    private static boolean isValidBirthday(LocalDate dateOfBirth) {
//        return dateOfBirth != null && dateOfBirth.isBefore(LocalDate.now());
//    }
//
//    @Override
//    public String toString() {
//        return "Personendaten{" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", titel='" + titel + '\'' +
//                ", dateOfBirth=" + dateOfBirth +
//                '}';
//    }
//}
