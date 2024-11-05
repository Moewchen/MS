package com.bht.MediTrack.Services;

import com.bht.MediTrack.Entities.Adresse;
import com.bht.MediTrack.Entities.Arzt;
import com.bht.MediTrack.Entities.Patient;
import com.bht.MediTrack.Repositories.ArztRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ArztverwaltungsserviceTest {

    @InjectMocks
    private Arztverwaltungsservice ArztService;

    @Mock
    private ArztRepository ArztRepo;

    private Arzt arzt;
    private Arzt arzt2;
    private UUID arztId;
    private List<Arzt> arztList;
    private List<Arzt> foundArzt;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        arztId = UUID.randomUUID();
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
        arzt2 = new Arzt(
                "Zahnarzt",
                "Alex",
                "Meister",
                "Dr.",
                LocalDate.of(1980,11,18),
                "meister@arzt.de",
                "015050505",
                new Adresse("Hauptdamm","22","012345","Berlin","Deutschland")
        );

        arztList = new ArrayList<>();
        arztList.add(arzt);
        arztList.add(arzt2);
    }

    @Test
    void testgetArztById() {
        when(ArztRepo.findArztById(arztId)).thenReturn(Optional.of(arzt));
        Arzt foundArzt = ArztService.getArztById(arztId);
        assertThat(foundArzt).isNotNull();
        assertThat(foundArzt.getFirstName()).isEqualTo("Tom");
        verify(ArztRepo, times(1)).findArztById(arztId);
    }

    @Test
    void testgetArztByName() {
        foundArzt = ArztService.getArztByName("Alex", "Meister");
        assertThat(foundArzt).isNotEmpty();
        assertThat(foundArzt.get(0).getFirstName()).isEqualTo("Alex");
        verify(ArztRepo, times(1)).findArztByName("Alex", "Meister");
    }

    @Test
    void testgetArztByFachrichtung() {
        foundArzt = ArztService.getArztByFachrichtung("Zahnarzt");
        assertThat(foundArzt).isNotEmpty();
        assertThat(foundArzt.get(0).getFachrichtung()).isEqualTo("Zahnarzt");
        verify(ArztRepo, times(1)).findArztByFachrichtung("Zahnarzt");
    }
}