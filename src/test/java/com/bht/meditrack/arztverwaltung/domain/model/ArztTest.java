package com.bht.meditrack.arztverwaltung.domain.model;

import com.bht.meditrack.arztverwaltung.domain.valueojects.Fachrichtung;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
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
                new Fachrichtung("Allgemeinmedizin"),
                new Personendaten("Tom", "Müller", "Dr. Med.", LocalDate.of(1976,3,1)),
                new Kontaktdaten("mueller@arzt.de", "015050505"),
                new Adresse("Hauptdamm","22", "01234", "Berlin")
        );
    }
    @Test
    void testGetters() {
        assertThat(arzt.getPersonendaten().firstName()).isEqualTo("Tom");
        assertThat(arzt.getPersonendaten().lastName()).isEqualTo("Müller");
        assertThat(arzt.getPersonendaten().titel()).isEqualTo("Dr. Med.");
        assertThat(arzt.getPersonendaten().dateOfBirth()).isEqualTo(LocalDate.of(1976, 3, 1));
        assertThat(arzt.getKontaktdaten().email()).isEqualTo("mueller@arzt.de");
        assertThat(arzt.getKontaktdaten().telefon()).isEqualTo("015050505");
        assertThat(arzt.getAdresse()).isEqualTo(new Adresse("Hauptdamm","22", "01234", "Berlin"));
        assertThat(arzt.getFachrichtung()).isEqualTo(new Fachrichtung("Allgemeinmedizin"));
    }
    @Test
    void testSetters() {
        arzt.setId(uuid);
        arzt.setFachrichtung(new Fachrichtung("Zahnarzt"));
        assertThat(arzt.getId()).isEqualTo(uuid);
        assertThat(arzt.getFachrichtung()).isEqualTo(new Fachrichtung("Zahnarzt"));
    }
}