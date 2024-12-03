package com.bht.MediTrack.Arztverwaltung.application.services;

import java.time.LocalDate;
import java.util.List;

import com.bht.MediTrack.Arztverwaltung.domain.model.Arzt;
import com.bht.MediTrack.Arztverwaltung.domain.valueojects.Fachrichtung;
import com.bht.MediTrack.Arztverwaltung.infrastructure.repositories.ArztRepository;
import com.bht.MediTrack.shared.domain.valueobjects.Adresse;
import com.bht.MediTrack.shared.domain.valueobjects.Kontaktdaten;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ArztServiceTest {

    @InjectMocks
    private ArztService arztService;
    @Mock
    private ArztRepository arztRepository;
    private Arzt arzt;
    private UUID arztId;
    private Fachrichtung arztFachrichtung;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        arztId = UUID.randomUUID();
        arzt = new Arzt();
        arzt.setId(arztId);
        arzt.setFachrichtung(arztFachrichtung);
    }
    @Test
    void testGetArztById() {
        when(arztRepository.findArztById(arztId)).thenReturn(Optional.of(arzt));
        Optional<Arzt> result = arztService.findArztById(arztId);
        assertTrue(result.isPresent());
        assertEquals(arzt, result.get());
    }
    @Test
    public void testFindArztByIdNullId() {
        assertThrows(IllegalArgumentException.class, () -> arztService.findArztById(null), "ID darf nicht null sein.");
    }
    @Test
    public void testGetArztByNameEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> arztService.getArztByName(""), "Name darf nicht null oder leer sein.");
    }
    @Test
    public void testGetArztByNameNullName() {
        assertThrows(IllegalArgumentException.class, () -> arztService.getArztByName(null), "Name darf nicht null oder leer sein.");
    }
    @Test
    public void testGetArztByFachrichtungValidFachrichtung() {
        Arzt arzt = new Arzt(
                new Fachrichtung("Kardiologie"),
                new Personendaten("Tom", "Müller", "Dr. Med.", LocalDate.of(1976,3,1)),
                new Kontaktdaten("mueller@arzt.de", "015050505"),
                new Adresse("Hauptdamm","22", "01234", "Berlin")
        );
        when(arztRepository.findArztByFachrichtung("Kardiologie")).thenReturn(List.of(arzt));
        List<Arzt> result = arztService.getArztByFachrichtung("Kardiologie");
        assertEquals(1, result.size());
        assertEquals(new Fachrichtung("Kardiologie"), result.getFirst().getFachrichtung());
    }
    @Test
    public void testGetArztByFachrichtungEmpty() {
        assertThrows(IllegalArgumentException.class, () -> arztService.getArztByFachrichtung(""), "Fachrichtung darf nicht null oder leer sein.");
    }
    @Test
    public void testGetArztByFachrichtungNull() {
        assertThrows(IllegalArgumentException.class, () -> arztService.getArztByFachrichtung(null), "Fachrichtung darf nicht null oder leer sein.");
    }
}