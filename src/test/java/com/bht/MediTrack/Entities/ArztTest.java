package com.bht.MediTrack.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArztTest {

    private Arzt arzt;
    private UUID uuid = UUID.randomUUID();
    private String Fachrichtung = "Zahnarzt";

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
                new Adresse("Hauptdamm","22","012345","Berlin","Deutschland")
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
        //TODO: Test Adresse hinzufügen/verbessern
        //assertThat(arzt.getAdresse().toString()).isEqualTo("Hauptdamm 22, 012345 Berlin, Deutschland");
        assertThat(arzt.getFachrichtung()).isEqualTo("Allgemeinmedizin");
    }

    @Test
    void testSetters() {
        arzt.setArztId(uuid);
        arzt.setFachrichtung(Fachrichtung);

        assertThat(arzt.getArztId()).isEqualTo(uuid);
        assertThat(arzt.getFachrichtung()).isEqualTo(Fachrichtung);
    }

}