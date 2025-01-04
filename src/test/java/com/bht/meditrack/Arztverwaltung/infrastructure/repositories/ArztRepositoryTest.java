package com.bht.meditrack.Arztverwaltung.infrastructure.repositories;


import com.bht.meditrack.Arztverwaltung.domain.model.Arzt;
import com.bht.meditrack.Arztverwaltung.domain.valueojects.Fachrichtung;
import com.bht.meditrack.MediTrackApplication;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = MediTrackApplication.class)
class ArztRepositoryTest {

    @MockBean
    private ArztRepository arztRepository;


    private Arzt arzt;

    @BeforeEach
    void setUp() {
        LocalDate date = LocalDate.of(1994, 12, 12);
        arzt = new Arzt(
                new Fachrichtung("Zahnarzt"),
                new Personendaten("Marta","Müller","Frau Dr.",date),
                new Kontaktdaten("mueller@arzt.de","01010101"),
                new Adresse("Teststraße","90","01234","Stadt")
        );
        when(arztRepository.save(any(Arzt.class))).thenReturn(arzt);
        arztRepository.save(arzt);
    }

    @Test
    public void testSavedArzt(){
        LocalDate date = LocalDate.of(1994, 12, 12);
        Arzt newArzt = new Arzt(
                new Fachrichtung("Zahnarzt"),
                new Personendaten("Marta","Müller","Frau Dr.",date),
                new Kontaktdaten("mueller@arzt.de","01010101"),
                new Adresse("Teststraße","90","01234","Stadt")
        );
        Arzt savedArzt = arztRepository.save(newArzt);
        assertNotNull(savedArzt,"Arzt sollte vorhanden sein");
    }

    @Test
    public void testFindArztById(){
        when(arztRepository.findById(arzt.getId())).thenReturn(Optional.of(arzt));

        Optional<Arzt> foundArzt = arztRepository.findById(arzt.getId());
        assertTrue(foundArzt.isPresent(), "Arzt sollte vorhanden sein");
        assertEquals(arzt.getId(), foundArzt.get().getId());
        assertEquals(arzt.getPersonendaten().firstName(), foundArzt.get().getPersonendaten().firstName());
        assertEquals(arzt.getKontaktdaten().email(), foundArzt.get().getKontaktdaten().email());
    };
}