package com.bht.MediTrack.Entities;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NutzerTest {

    private Nutzer nutzer;

    @BeforeEach
    void setUp() {
        // Initialize a new Nutzer instance before each test
        nutzer = new Nutzer(
                "John",
                "Doe",
                "Dr.",
                LocalDate.of(1988, 12, 7),
                "john.doe@example.com",
                "+123456789",
                new Adresse("Frankfurter Allee", "12", "10115", "Berlin", "Deutschland")
        );
    }

    @Test
    void testGetters() {
        assertThat(nutzer.getFirstName()).isEqualTo("John");
        assertThat(nutzer.getLastName()).isEqualTo("Doe");
        assertThat(nutzer.getTitel()).isEqualTo("Dr.");
        assertThat(nutzer.getDateOfBirth()).isEqualTo(LocalDate.of(1988, 12, 7));
        assertThat(nutzer.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(nutzer.getTelefon()).isEqualTo("+123456789");
        assertThat(Adresse.getStrasse()).isEqualTo("Frankfurter Allee");
        assertThat(Adresse.getHausnummer()).isEqualTo("12");
        assertThat(Adresse.getPlz()).isEqualTo("10115");
        assertThat(Adresse.getStadt()).isEqualTo("Berlin");
        assertThat(Adresse.getLand()).isEqualTo("Deutschland");
    }


    @Test
    void testSetters() {
        nutzer.setFirstName("Jane");
        nutzer.setLastName("Smith");
        nutzer.setTitel("Prof.");
        nutzer.setDateOfBirth(LocalDate.of(1988, 12, 7));
        nutzer.setEmail("jane.smith@example.com");
        nutzer.setTelefon("+987654321");

        // Setze statische Adressfelder
        Adresse.setStrasse("Frankfurter Allee");
        Adresse.setHausnummer("12");
        Adresse.setPlz("80331");
        Adresse.setStadt("Munich");
        Adresse.setLand("Germany");

        assertThat(nutzer.getFirstName()).isEqualTo("Jane");
        assertThat(nutzer.getLastName()).isEqualTo("Smith");
        assertThat(nutzer.getTitel()).isEqualTo("Prof.");
        assertThat(nutzer.getDateOfBirth()).isEqualTo(LocalDate.of(1988, 12, 7));
        assertThat(nutzer.getEmail()).isEqualTo("jane.smith@example.com");
        assertThat(nutzer.getTelefon()).isEqualTo("+987654321");
        assertThat(Adresse.getStrasse()).isEqualTo("Frankfurter Allee");
        assertThat(Adresse.getHausnummer()).isEqualTo("12");
        assertThat(Adresse.getPlz()).isEqualTo("80331");
        assertThat(Adresse.getStadt()).isEqualTo("Munich");
        assertThat(Adresse.getLand()).isEqualTo("Germany");
    }

    @Test
    void testToString() {
        String expectedString = "Nutzer{" +
                "id=" + nutzer.getId() +
                ", firstName='John'" +
                ", lastName='Doe'" +
                ", titel='Dr.'" +
                ", dateOfBirth=1988-12-07" +
                ", email='john.doe@example.com'" +
                ", telefon='+123456789'" +
                ", adresse=Adresse{strasse='Frankfurter Allee', hausnummer='12', plz='10115', stadt='Berlin', land='Deutschland'}" +
                '}';

        assertThat(nutzer.toString()).isEqualTo(expectedString);
    }
}
