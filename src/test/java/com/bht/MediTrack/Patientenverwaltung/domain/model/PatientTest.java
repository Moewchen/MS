package com.bht.MediTrack.Patientenverwaltung.domain.model;

import com.bht.MediTrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient(
                UUID.randomUUID(),
                new Krankenkasse("AOK"),
                "123456789012",
                new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                new Kontaktdaten("max.mustermann@example.com", "01234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")
        );
    }

    @Test
    void testGettersAndSetters() {
        assertThat(patient.getPersonendaten().firstName()).isEqualTo("Max");
        assertThat(patient.getPersonendaten().lastName()).isEqualTo("Mustermann");
        assertThat(patient.getKrankenkasse().krankenkasse()).isEqualTo("AOK");
        assertThat(patient.getKrankenversicherungsnummer()).isEqualTo("123456789012");

        patient.setKrankenkasse(new Krankenkasse("Techniker"));
        patient.setKrankenversicherungsnummer("987654321098");

        assertThat(patient.getKrankenkasse().krankenkasse()).isEqualTo("Techniker");
        assertThat(patient.getKrankenversicherungsnummer()).isEqualTo("987654321098");
    }

    @Test
    void testKrankenkasseAndVersicherungsnummerFormat() {
        assertThat(patient.getKrankenkasse().krankenkasse()).matches("^[A-Za-z]+$");
        assertThat(patient.getKrankenversicherungsnummer()).matches("^\\d{12}$");

        patient.setKrankenkasse(new Krankenkasse("AOK123"));
        patient.setKrankenversicherungsnummer("12345ABC");

        assertThat(patient.getKrankenkasse().krankenkasse()).doesNotMatch("^[A-Za-z]+$");
        assertThat(patient.getKrankenversicherungsnummer()).doesNotMatch("^\\d{12}$");
    }

    @Test
    void testPatientAsAggregateRoot() {
        UUID originalId = patient.getId();
        assertThat(originalId).isNotNull();

        patient.setId(UUID.randomUUID());
        assertThat(patient.getId()).isNotEqualTo(originalId);
    }

    @Test
    void testPatientListWithStreamsAndLambdas() {
        List<Patient> patients = Arrays.asList(
                new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "123456789012",
                        new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                        new Kontaktdaten("max.mustermann@example.com", "01234567890"),
                        new Adresse("Musterstraße", "1", "12345", "Musterstadt")),
                new Patient(UUID.randomUUID(), new Krankenkasse("TK"), "987654321098",
                        new Personendaten("Anna", "Anders", "Prof.", LocalDate.of(1990, 2, 10)),
                        new Kontaktdaten("anna.anders@example.com", "0876543210"),
                        new Adresse("Hauptstraße", "2", "22222", "Hauptstadt")),
                new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "112233445566",
                        new Personendaten("Erika", "Meier", "Dr.", LocalDate.of(1980, 3, 5)),
                        new Kontaktdaten("erika.meier@example.com", "0765432100"),
                        new Adresse("Nebenstraße", "3", "33333", "Nebenstadt"))
        );

        List<Patient> aokPatients = patients.stream()
                .filter(p -> "AOK".equals(p.getKrankenkasse().krankenkasse()))
                .toList();

        assertThat(aokPatients).hasSize(2);
        assertThat(aokPatients).extracting(Patient::getKrankenkasse)
                .extracting(Krankenkasse::krankenkasse)
                .containsOnly("AOK");
    }

    @Test
    void testPatientListToSet() {
        List<Patient> patients = Arrays.asList(
                new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "123456789012",
                        new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                        new Kontaktdaten("max.mustermann@example.com", "091234567890"),
                        new Adresse("Musterstraße", "1", "12345", "Musterstadt")),
                new Patient(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), new Krankenkasse("TK"), "987654321098",
                        new Personendaten("Anna", "Anders", "Prof.", LocalDate.of(1990, 2, 10)),
                        new Kontaktdaten("anna.anders@example.com", "0876543210"),
                        new Adresse("Hauptstraße", "2", "22222", "Hauptstadt")),
                new Patient(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), new Krankenkasse("TK"), "987654321098",
                        new Personendaten("Anna", "Anders", "Prof.", LocalDate.of(1990, 2, 10)),
                        new Kontaktdaten("anna.anders@example.com", "0876543210"),
                        new Adresse("Hauptstraße", "2", "22222", "Hauptstadt"))
        );

        Set<Patient> uniquePatients = new HashSet<>(patients);
        assertThat(uniquePatients).hasSize(2);
    }

    @Test
    void testEqualsAndHashCode() {
        UUID id = UUID.randomUUID();
        Patient patient1 = new Patient(id, new Krankenkasse("AOK"), "123456789012",
                new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                new Kontaktdaten("max.mustermann@example.com", "01234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt"));
        Patient patient2 = new Patient(id, new Krankenkasse("AOK"), "123456789012",
                new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                new Kontaktdaten("max.mustermann@example.com", "01234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt"));

        assertThat(patient1).isEqualTo(patient2);
        assertThat(patient1.hashCode()).isEqualTo(patient2.hashCode());
    }

    @Test
    void testInvalidPersonendaten() {
        assertThatThrownBy(() -> new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "123456789012",
                new Personendaten("", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                new Kontaktdaten("max.mustermann@example.com", "01234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Vorname darf nicht leer sein");

        assertThatThrownBy(() -> new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "123456789012",
                new Personendaten("Max", "", "Dr.", LocalDate.of(1985, 5, 20)),
                new Kontaktdaten("max.mustermann@example.com", "01234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Nachname darf nicht leer sein");

        assertThatThrownBy(() -> new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "123456789012",
                new Personendaten("Max", "Mustermann", "", LocalDate.of(1985, 5, 20)),
                new Kontaktdaten("max.mustermann@example.com", "01234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Titel darf nicht leer sein");

        assertThatThrownBy(() -> new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "123456789012",
                new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(2100, 5, 20)),
                new Kontaktdaten("max.mustermann@example.com", "01234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Geburtsdatum darf nicht in der Zukunft liegen");
    }

    @Test
    void testInvalidKontaktdaten() {
        assertThatThrownBy(() -> new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "123456789012",
                new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                new Kontaktdaten("", "01234567890"),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email darf nicht leer sein");

        assertThatThrownBy(() -> new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "123456789012",
                new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                new Kontaktdaten("max.mustermann@example.com", ""),
                new Adresse("Musterstraße", "1", "12345", "Musterstadt")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Telefonnummer darf nicht leer sein");
    }
}
