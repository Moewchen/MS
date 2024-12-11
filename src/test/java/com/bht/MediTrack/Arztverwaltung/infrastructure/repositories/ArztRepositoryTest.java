package com.bht.MediTrack.Arztverwaltung.infrastructure.repositories;


import com.bht.MediTrack.Arztverwaltung.domain.model.Arzt;
import com.bht.MediTrack.Arztverwaltung.domain.valueojects.Fachrichtung;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

class ArztRepositoryTest {
/*
    private ArztRepository arztRepository;
    @BeforeEach
    void setUp() {
        arztRepository = new ArztRepository();
    }
    @Test
    public void TestFindArztById() {
        Arzt arzt = new Arzt(
                new Fachrichtung("Allgemeinmedizin"),
                new Personendaten("Tom", "Müller", "Dr. Med.", LocalDate.of(1976,3,1)),
                new Kontaktdaten("mueller@arzt.de", "015050505"),
                new Adresse("Hauptdamm","22", "01234", "Berlin")
        );
        Arzt savedArzt = arztRepository.save(arzt);
        Optional<Arzt> foundArzt = arztRepository.findArztById(savedArzt.getId());
        assertThat(foundArzt).isPresent();
        assertThat(foundArzt.get().getPersonendaten().firstName()).isEqualTo("Tom");
    }
 */
}