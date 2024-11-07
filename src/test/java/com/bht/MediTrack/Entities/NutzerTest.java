package com.bht.MediTrack.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.regex.Pattern;
import static org.assertj.core.api.Assertions.assertThat;

class NutzerTest {

    private Nutzer nutzer;

    @BeforeEach
    void setUp() {
        nutzer = new Nutzer(
                "Max",
                "Mustermann",
                "Dr.",
                LocalDate.of(1985, 5, 20),
                "+491234567890",
                "max.mustermann@example.com",
                "Musterstraße 1, 12345 Musterstadt"
        );
    }

    @Test
    void testGetters() {
        assertThat(nutzer.getFirstName()).isEqualTo("Max");
        assertThat(nutzer.getLastName()).isEqualTo("Mustermann");
        assertThat(nutzer.getTitel()).isEqualTo("Dr.");
        assertThat(nutzer.getDateOfBirth()).isEqualTo(LocalDate.of(1985, 5, 20));
        assertThat(nutzer.getTelefon()).isEqualTo("+491234567890");
        assertThat(nutzer.getEmail()).isEqualTo("max.mustermann@example.com");
        assertThat(nutzer.getAdresse()).isEqualTo("Musterstraße 1, 12345 Musterstadt");
    }

    @Test
    void testSetters() {
        nutzer.setFirstName("Erika");
        nutzer.setLastName("Musterfrau");
        nutzer.setTitel("Prof.");
        nutzer.setDateOfBirth(LocalDate.of(1990, 1, 15));
        nutzer.setTelefon("+4987654321");
        nutzer.setEmail("erika.musterfrau@example.com");
        nutzer.setAdresse("Hauptstraße 2, 67890 Beispielstadt");

        assertThat(nutzer.getFirstName()).isEqualTo("Erika");
        assertThat(nutzer.getLastName()).isEqualTo("Musterfrau");
        assertThat(nutzer.getTitel()).isEqualTo("Prof.");
        assertThat(nutzer.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 1, 15));
        assertThat(nutzer.getTelefon()).isEqualTo("+4987654321");
        assertThat(nutzer.getEmail()).isEqualTo("erika.musterfrau@example.com");
        assertThat(nutzer.getAdresse()).isEqualTo("Hauptstraße 2, 67890 Beispielstadt");
    }

    @Test
    void testEmailFormat() {
        // Regex für gültiges E-Mail-Format
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        assertThat(emailPattern.matcher(nutzer.getEmail()).matches()).isTrue();

        // Test für ungültige E-Mail
        nutzer.setEmail("ungültig-email");
        assertThat(emailPattern.matcher(nutzer.getEmail()).matches()).isFalse();
    }

    @Test
    void testTelefonFormat() {
        // Regex für Telefonnummer (internationales Format, z.B. +49 123 456 7890)
        Pattern telefonPattern = Pattern.compile("^\\+\\d{1,3}\\d{4,14}(?:x.+)?$");
        assertThat(telefonPattern.matcher(nutzer.getTelefon()).matches()).isTrue();

        // Test für ungültige Telefonnummer
        nutzer.setTelefon("123abc");
        assertThat(telefonPattern.matcher(nutzer.getTelefon()).matches()).isFalse();
    }

    @Test
    void testAdresseFormat() {
        // Address format: "Straßenname Hausnummer, PLZ Stadt"
        Pattern adressePattern = Pattern.compile("^[A-Za-zäöüÄÖÜß\\s]+\\s\\d+,\\s\\d{5}\\s[A-Za-zäöüÄÖÜß\\s]+$");

        // Test valid address format
        assertThat(adressePattern.matcher(nutzer.getAdresse()).matches()).isTrue();

        // Test invalid addresses
        nutzer.setAdresse("UnvollständigeAdresse123");
        assertThat(adressePattern.matcher(nutzer.getAdresse()).matches()).isFalse();

        nutzer.setAdresse("Straße ohne PLZ Stadt");
        assertThat(adressePattern.matcher(nutzer.getAdresse()).matches()).isFalse();
    }

    @Test
    void testEmptyAdresse() {
        // Test empty or null address
        nutzer.setAdresse(null);
        assertThat(nutzer.getAdresse()).isNull();

        nutzer.setAdresse("");
        assertThat(nutzer.getAdresse()).isEmpty();
    }

    @Test
    void testEqualsAndHashCode() {
        Nutzer anotherNutzer = new Nutzer(
                "Max",
                "Mustermann",
                "Dr.",
                LocalDate.of(1985, 5, 20),
                "+491234567890",
                "max.mustermann@example.com",
                "Musterstraße 1, 12345 Musterstadt"
        );
        anotherNutzer.setId(nutzer.getId());

        assertThat(nutzer).isEqualTo(anotherNutzer);
        assertThat(nutzer.hashCode()).isEqualTo(anotherNutzer.hashCode());
    }

    @Test
    void testNotEquals() {
        Nutzer differentNutzer = new Nutzer(
                "Anna",
                "Anders",
                "Mrs.",
                LocalDate.of(1992, 8, 12),
                "+49876543210",
                "anna.anders@example.com",
                "Anderstraße 3, 67890 Beispielstadt"
        );

        assertThat(nutzer).isNotEqualTo(differentNutzer);
    }

    @Test
    void testNullFields() {
        // Teste, dass die Felder bei einem neuen Nutzer-Objekt nicht null sind
        Nutzer newNutzer = new Nutzer();
        assertThat(newNutzer.getFirstName()).isNull();
        assertThat(newNutzer.getLastName()).isNull();
        assertThat(newNutzer.getTitel()).isNull();
        assertThat(newNutzer.getDateOfBirth()).isNull();
        assertThat(newNutzer.getEmail()).isNull();
        assertThat(newNutzer.getTelefon()).isNull();
        assertThat(newNutzer.getAdresse()).isNull();
    }
}
