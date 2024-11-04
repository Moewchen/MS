package com.bht.MediTrack.Entities;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private Patient patient;
    private Krankenkasse krankenkasse;

    @BeforeEach
    void setUp() {
        krankenkasse = new Krankenkasse("AOK", "123456789");
        patient = new Patient(
                "John",
                "Doe",
                "Dr.",
                LocalDate.of(1980, 12, 7),
                "john.doe@example.com",
                "+123456789",
                new Adresse("Main St", "123", "10115", "Berlin", "Germany"),
                krankenkasse
        );
    }


    @Test
    void testGetters() {
        assertThat(patient.getFirstName()).isEqualTo("John");
        assertThat(patient.getLastName()).isEqualTo("Doe");
        assertThat(patient.getTitel()).isEqualTo("Dr.");
        assertThat(patient.getDateOfBirth()).isEqualTo(LocalDate.of(1980, 12, 7));
        assertThat(patient.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(patient.getTelefon()).isEqualTo("+123456789");

        assertThat(patient.getKrankenkasse()).isEqualTo(krankenkasse);
        assertThat(patient.getKrankenkasse().getKrankenkasseName()).isEqualTo("AOK");
        assertThat(patient.getKrankenkasse().getKrankenversicherungsnummer()).isEqualTo("123456789");
    }

    @Test
    void testSetters() {
        Krankenkasse neueKrankenkasse = new Krankenkasse("Techniker Krankenkasse", "987654321");
        patient.setKrankenkasse(neueKrankenkasse);

        assertThat(patient.getKrankenkasse()).isEqualTo(neueKrankenkasse);
        assertThat(patient.getKrankenkasse().getKrankenkasseName()).isEqualTo("Techniker Krankenkasse");
        assertThat(patient.getKrankenkasse().getKrankenversicherungsnummer()).isEqualTo("987654321");
    }

    @Test
    void testToString() {
        String expectedString = "Patient{" +
                "id=" + patient.getId() +
                ", firstName='John'" +
                ", lastName='Doe'" +
                ", krankenkasse=Krankenkasse{krankenkasseName='AOK', krankenversicherungsnummer='123456789'}" +
                '}';
        assertThat(patient.toString()).contains(expectedString);
    }
}
