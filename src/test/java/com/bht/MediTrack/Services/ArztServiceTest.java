package com.bht.MediTrack.Services;

import com.bht.MediTrack.Entities.Arzt;
import com.bht.MediTrack.Repositories.ArztRepository;
import java.time.LocalDate;
import java.util.List;
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
    private String arztFachrichtung;

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
        Arzt arzt = new Arzt("Kardiologie", "Anna", "Müller", "Dr.", LocalDate.of(1975, 5, 10), "+491234567890", "anna@example.com", "Straße 1");
        when(arztRepository.findArztByFachrichtung("Kardiologie")).thenReturn(List.of(arzt));

        List<Arzt> result = arztService.getArztByFachrichtung("Kardiologie");
        assertEquals(1, result.size());
        assertEquals("Kardiologie", result.getFirst().getFachrichtung());
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
