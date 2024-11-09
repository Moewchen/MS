package com.bht.MediTrack.Services;

import com.bht.MediTrack.Entities.Arzt;
import com.bht.MediTrack.Repositories.ArztRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ArztServiceTest {

    @InjectMocks
    private ArztService ArztService;

    @Mock
    private ArztRepository ArztRepository;
    private Arzt arzt;
    private UUID arztId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        arztId = UUID.randomUUID();
        arzt = new Arzt();
        arzt.setId(arztId);
    }

    @Test
    void testGetArztById() {
        when(ArztRepository.findArztById(arztId)).thenReturn(Optional.of(arzt));

        Optional<Arzt> result = ArztService.findArztById(arztId);
        assertTrue(result.isPresent());
        assertEquals(arzt, result.get());
    }
}