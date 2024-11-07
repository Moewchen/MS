package com.bht.MediTrack.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import static org.assertj.core.api.Assertions.assertThat;

class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient(
                "Max",
                "Mustermann",
                "Dr.",
                LocalDate.of(1985, 5, 20),
                "+491234567890",
                "max.mustermann@example.com",
                "Musterstraße 1, 12345 Musterstadt",
                "AOK",
                "123456789012"
        );
    }

    // Test für Getter und Setter der Klasse
    @Test
    void testGettersAndSetters() {
        assertThat(patient.getFirstName()).isEqualTo("Max");
        assertThat(patient.getLastName()).isEqualTo("Mustermann");
        assertThat(patient.getKrankenkasse()).isEqualTo("AOK");
        assertThat(patient.getKrankenversicherungsnummer()).isEqualTo("123456789012");

        patient.setKrankenkasse("Techniker");
        patient.setKrankenversicherungsnummer("987654321098");

        assertThat(patient.getKrankenkasse()).isEqualTo("Techniker");
        assertThat(patient.getKrankenversicherungsnummer()).isEqualTo("987654321098");
    }

    // Test für Krankenkasse und Versicherungsnummer mit Regex
    @Test
    void testKrankenkasseAndVersicherungsnummerFormat() {
        // Regex für gültige Krankenkasse (beispielsweise nur Buchstaben)
        Pattern krankenkassePattern = Pattern.compile("^[A-Za-z]+$");
        assertThat(krankenkassePattern.matcher(patient.getKrankenkasse()).matches()).isTrue();

        // Test für ungültige Krankenkasse
        patient.setKrankenkasse("AOK123");
        assertThat(krankenkassePattern.matcher(patient.getKrankenkasse()).matches()).isFalse();

        // Regex für gültige Versicherungsnummer (numerisch, 12 Ziffern)
        Pattern versicherungsnummerPattern = Pattern.compile("^\\d{12}$");
        assertThat(versicherungsnummerPattern.matcher(patient.getKrankenversicherungsnummer()).matches()).isTrue();

        // Test für ungültige Versicherungsnummer
        patient.setKrankenversicherungsnummer("12345ABC");
        assertThat(versicherungsnummerPattern.matcher(patient.getKrankenversicherungsnummer()).matches()).isFalse();
    }

    // Test für das Aggregat-Root des Patienten (DDD-Modell)
    @Test
    void testPatientAsAggregateRoot() {
        // Im DDD sind Entitäten wie Patient Aggregate-Roots und sollten alle Geschäftsfunktionen enthalten
        // Hier prüfen wir, ob der Patient korrekt als Aggregat behandelt wird, indem wir ein neues Objekt erstellen und sicherstellen, dass es ein konsistentes Aggregat ist.

        UUID originalId = patient.getId(); // ID bleibt beim Erstellen von Patienten gleich
        assertThat(originalId).isNull();

        // Setze eine neue ID und überprüfe
        patient.setId(UUID.randomUUID());
        assertThat(patient.getId()).isNotNull();
    }

    // Test für eine Liste von Patienten, mit Streams und Lambdas
    @Test
    void testPatientListWithStreamsAndLambdas() {
        // Eine Liste von Patienten erstellen
        List<Patient> patients = Arrays.asList(
                new Patient("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20), "+491234567890", "max.mustermann@example.com", "Musterstraße 1", "AOK", "123456789012"),
                new Patient("Anna", "Anders", "Prof.", LocalDate.of(1990, 2, 10), "+4987654321", "anna.anders@example.com", "Hauptstraße 2", "TK", "987654321098"),
                new Patient("Erika", "Meier", "Dr.", LocalDate.of(1980, 3, 5), "+4976543210", "erika.meier@example.com", "Nebenstraße 3", "AOK", "112233445566")
        );

        // Filtere die Liste der Patienten, die die AOK als Krankenkasse haben
        List<Patient> aokPatients = new ArrayList<>();
        patients.stream()
                .filter(p -> "AOK".equals(p.getKrankenkasse()))  // Filtere nach AOK
                .forEach(aokPatients::add);

        assertThat(aokPatients).hasSize(2);
        assertThat(aokPatients).extracting(Patient::getKrankenkasse).containsOnly("AOK");
    }

    // Test für die Umwandlung einer Liste von Patienten in ein Set (Verwendung von Generics)
    @Test
    void testPatientListToSet() {
        List<Patient> patients = Arrays.asList(
                new Patient("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20), "+491234567890", "max.mustermann@example.com", "Musterstraße 1", "AOK", "123456789012"),
                new Patient("Anna", "Anders", "Prof.", LocalDate.of(1990, 2, 10), "+4987654321", "anna.anders@example.com", "Hauptstraße 2", "TK", "987654321098"),
                new Patient("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20), "+491234567890", "max.mustermann@example.com", "Musterstraße 1", "AOK", "123456789012")
        );

        // Verwende Streams, um die Patienten-Liste in ein Set zu konvertieren (doppelte Patienten entfernen)
        Set<Patient> uniquePatients = new HashSet<>(patients);

        assertThat(uniquePatients).hasSize(2); // Nur zwei Patienten, da der zweite Max als Duplikat entfernt wird
    }
}
