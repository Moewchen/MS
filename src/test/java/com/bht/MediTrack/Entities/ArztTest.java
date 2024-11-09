package com.bht.MediTrack.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ArztTest {

    private Arzt arzt;
    private final UUID uuid = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        arzt = new Arzt(
                "Allgemeinmedizin",
                "Tom",
                "Müller",
                "Dr. Med.",
                LocalDate.of(1976,3,1),
                "müller@arzt.de",
                "015050505",
                "Hauptdamm 22, 012345 Berlin"
        );
    }

    @Test
    void testGetters() {
        assertThat(arzt.getFirstName()).isEqualTo("Tom");
        assertThat(arzt.getLastName()).isEqualTo("Müller");
        assertThat(arzt.getTitel()).isEqualTo("Dr. Med.");
        assertThat(arzt.getDateOfBirth()).isEqualTo(LocalDate.of(1976, 3, 1));
        assertThat(arzt.getEmail()).isEqualTo("müller@arzt.de");
        assertThat(arzt.getTelefon()).isEqualTo("015050505");
        assertThat(arzt.getAdresse()).isEqualTo("Hauptdamm 22, 012345 Berlin");
        assertThat(arzt.getFachrichtung()).isEqualTo("Allgemeinmedizin");
    }

    @Test
    void testSetters() {
        arzt.setId(uuid);
        String fachrichtung = "Zahnarzt";
        arzt.setFachrichtung(fachrichtung);

        assertThat(arzt.getId()).isEqualTo(uuid);
        assertThat(arzt.getFachrichtung()).isEqualTo(fachrichtung);
    }

}