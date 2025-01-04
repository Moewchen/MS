package com.bht.meditrack.Arztverwaltung.application.services;

import java.time.LocalDate;
import java.util.List;

import com.bht.meditrack.Arztverwaltung.domain.model.Arzt;
import com.bht.meditrack.Arztverwaltung.domain.valueojects.Fachrichtung;
import com.bht.meditrack.Arztverwaltung.infrastructure.repositories.ArztRepository;
import com.bht.meditrack.shared.domain.valueobjects.Adresse;
import com.bht.meditrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.meditrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ArztServiceTest {

    @Mock
    private ArztRepository arztRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private ArztService arztService;


    private Arzt arzt;
    private UUID arztId;

    @BeforeEach
    void setUp() {
        arztId = UUID.randomUUID();
        arzt = new Arzt();
        arzt.setId(arztId);
        arzt.setFachrichtung(new Fachrichtung("Zahnarzt"));
        arzt.setPersonendaten(new Personendaten("Marta", "Müller", "Frau Dr.", LocalDate.of(1994, 12, 12)));
        arzt.setKontaktdaten(new Kontaktdaten("mueller@arzt.de", "01010101"));
        arzt.setAdresse(new Adresse("Teststraße", "90", "01234", "Stadt"));
    }

    @Test
    void testUpsertArzt() {
        when(arztRepository.save(any(Arzt.class))).thenReturn(arzt);

        Arzt savedArzt = arztService.upsertArzt(arztId, arzt);

        assertNotNull(savedArzt, "Arzt sollte nicht null sein");
        assertEquals(arztId, savedArzt.getId(), "Die ID des Arztes sollte übereinstimmen");
    }

    @Test
    void testFindArztById() {
        // Simuliere das Verhalten des Repositories
        when(arztRepository.findById(arztId)).thenReturn(Optional.of(arzt));

        Arzt foundArzt = arztService.findArztById(arztId);

        assertNotNull(foundArzt, "Arzt sollte gefunden werden");
        assertEquals(arztId, foundArzt.getId(), "Die ID des Arztes sollte übereinstimmen");
    }

    @Test
    void testFindArztByIdNotFound() {
        when(arztRepository.findById(arztId)).thenReturn(Optional.empty());

        Arzt foundArzt = arztService.findArztById(arztId);

        assertNull(foundArzt, "Arzt sollte nicht gefunden werden");
    }

    @Test
    void testGetAllAerzte() {

        when(arztRepository.findAll()).thenReturn(List.of(arzt));

        List<Arzt> aerzte = arztService.getAllAerzte();

        assertNotNull(aerzte, "Liste der Ärzte sollte nicht null sein");
        assertEquals(1, aerzte.size(), "Es sollte genau ein Arzt in der Liste sein");
    }

}