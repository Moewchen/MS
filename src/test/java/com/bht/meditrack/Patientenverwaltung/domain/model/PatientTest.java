package com.bht.meditrack.Patientenverwaltung.domain.model;

import com.bht.meditrack.Patientenverwaltung.domain.valueojects.Krankenkasse;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(UUID.randomUUID());
        patient.setKrankenkasse(new Krankenkasse("AOK"));
        patient.setKrankenversicherungsnummer("123456789012");
        patient.setPersonendaten(new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)));
        patient.setKontaktdaten(new Kontaktdaten("max.mustermann@example.com", "01234567890"));
        patient.setAdresse(new Adresse("Musterstraße", "1", "12345", "Musterstadt"));
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
        Pattern krankenkassePattern = Pattern.compile("^[A-Za-z]+$");
        assertThat(krankenkassePattern.matcher(patient.getKrankenkasse().krankenkasse()).matches()).isTrue();

        patient.setKrankenkasse(new Krankenkasse("AOK123"));
        assertThat(krankenkassePattern.matcher(patient.getKrankenkasse().krankenkasse()).matches()).isFalse();

        Pattern versicherungsnummerPattern = Pattern.compile("^\\d{12}$");
        assertThat(versicherungsnummerPattern.matcher(patient.getKrankenversicherungsnummer()).matches()).isTrue();

        patient.setKrankenversicherungsnummer("12345ABC");
        assertThat(versicherungsnummerPattern.matcher(patient.getKrankenversicherungsnummer()).matches()).isFalse();
    }

    @Test
    void testPatientAsAggregateRoot() {
        UUID originalId = patient.getId();
        assertThat(originalId).isNotNull();

        patient.setId(UUID.randomUUID());
        assertThat(patient.getId()).isNotNull();
    }

    @Test
    void testPatientListWithStreamsAndLambdas() {
        List<Patient> patients = List.of(
                new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "123456789012",
                        new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                        new Kontaktdaten("max.mustermann@example.com", "01234567890"),
                        new Adresse("Musterstraße", "1", "12345", "Musterstadt")),
                new Patient(UUID.randomUUID(), new Krankenkasse("TK"), "987654321098",
                        new Personendaten("Anna", "Anders", "Prof.", LocalDate.of(1990, 2, 10)),
                        new Kontaktdaten("anna.anders@example.com", "087654321"),
                        new Adresse("Hauptstraße", "2", "22222", "Musterstadt")),
                new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "112233445566",
                        new Personendaten("Erika", "Meier", "Dr.", LocalDate.of(1980, 3, 5)),
                        new Kontaktdaten("erika.meier@example.com", "076543210"),
                        new Adresse("Nebenstraße", "3", "33333", "Musterstadt"))
        );

        List<Patient> aokPatients = patients.stream()
                .filter(p -> "AOK".equals(p.getKrankenkasse().krankenkasse()))
                .toList();

        assertThat(aokPatients).hasSize(2);
        assertThat(aokPatients).extracting(Patient::getKrankenkasse).extracting(Krankenkasse::krankenkasse).containsOnly("AOK");
    }

    @Test
    void testPatientListToSet() {
        List<Patient> patients = List.of(
                new Patient(UUID.randomUUID(), new Krankenkasse("AOK"), "123456789012",
                        new Personendaten("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20)),
                        new Kontaktdaten("max.mustermann@example.com", "01234567890"),
                        new Adresse("Musterstraße", "1", "12345", "Musterstadt")),
                new Patient(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), new Krankenkasse("TK"), "987654321098",
                        new Personendaten("Anna", "Anders", "Prof.", LocalDate.of(1990, 2, 10)),
                        new Kontaktdaten("anna.anders@example.com", "087654321"),
                        new Adresse("Hauptstraße", "2", "22222", "Musterstadt")),
                new Patient(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), new Krankenkasse("TK"), "987654321098",
                        new Personendaten("Anna", "Anders", "Prof.", LocalDate.of(1990, 2, 10)),
                        new Kontaktdaten("anna.anders@example.com", "087654321"),
                        new Adresse("Hauptstraße", "2", "22222", "Musterstadt"))
        );

        Set<Patient> uniquePatients = new HashSet<>(patients);
        assertThat(uniquePatients).hasSize(3);
    }
}
