package com.bht.MediTrack;

import com.bht.MediTrack.Entities.Adresse;
import com.bht.MediTrack.Entities.Arzt;
import com.bht.MediTrack.Repositories.ArztRepository;
import com.bht.MediTrack.Services.Arztverwaltungsservice;
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
    private UUID arztId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        arztId = UUID.randomUUID();
        arzt = new Arzt(
                "Zahnarzt",
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
    void testgetArztById() {
        when(ArztRepo.findArztById(arztId)).thenReturn(Optional.of(arzt));
        Arzt foundArzt = ArztService.getArztById(arztId);
        assertThat(foundArzt).isNotNull();
        assertThat(foundArzt.getFirstName()).isEqualTo("Tom");
        verify(ArztRepo, times(1)).findArztById(arztId);
    }
    @Test
    void testgetArztByName() {
        List<Arzt> arztList = new ArrayList<>();
        arztList.add(arzt);
        when(ArztRepo.findArztByName("Tom", "Müller")).thenReturn(arztList);
        List<Arzt> foundArzt = ArztService.getArztByName("Tom", "Müller");
        assertThat(foundArzt).isNotEmpty();
        assertThat(foundArzt.get(0).getFirstName()).isEqualTo("Tom");
        verify(ArztRepo, times(1)).findArztByName("Tom", "Müller");
    }

    @Test
    void testgetArztByFachrichtung() {
        List<Arzt> arztList = new ArrayList<>();
        arztList.add(arzt);
        when(ArztRepo.findArztByFachrichtung("Zahnarzt")).thenReturn(arztList);
        List<Arzt> foundArzt = ArztService.getArztByFachrichtung("Zahnarzt");
        assertThat(foundArzt).isNotEmpty();
        assertThat(foundArzt.get(0).getFachrichtung()).isEqualTo("Zahnarzt");
        verify(ArztRepo, times(1)).findArztByFachrichtung("Zahnarzt");
    }
}


