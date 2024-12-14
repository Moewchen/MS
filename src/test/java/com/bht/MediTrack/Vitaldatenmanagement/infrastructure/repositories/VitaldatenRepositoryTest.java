package com.bht.MediTrack.Vitaldatenmanagement.infrastructure.repositories;

import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class VitaldatenRepositoryTest {

    /*
    private VitaldatenRepository repository;
    private UUID patientId;
    private Vitaldaten vitaldaten;
    @BeforeEach
    void setUp() {
        repository = new VitaldatenRepository();
        patientId = UUID.randomUUID();
        vitaldaten = new Vitaldaten();
        vitaldaten.setId(patientId);
    }
    @Test
    void testCreateVitaldaten() {
        Vitaldaten created = repository.createVitaldaten(patientId,vitaldaten);
        assertEquals(vitaldaten, created);
        assertTrue(repository.getVitaldatenById(patientId).isPresent());
    }
    @Test
    void testGetVitaldatenByPatientenId() {
        repository.createVitaldaten(patientId,vitaldaten);
        Optional<Vitaldaten> found = repository.getVitaldatenByPatientenId(patientId);
        assertTrue(found.isPresent());
        assertEquals(vitaldaten, found.get());
    }

/*    @Test
    void testUpdateVitaldaten() {
        repository.createVitaldaten(patientId,vitaldaten);
        Vitaldaten updatedVitaldaten = new Vitaldaten();
        updatedVitaldaten.setId(patientId);
        boolean updated = repository.updateVitaldaten(patientId, updatedVitaldaten);
        assertTrue(updated);
        assertEquals(updatedVitaldaten, repository.getVitaldatenById(patientId).get());
    }*/
    /*
    @Test
    void testGetVitaldatenById() {
        repository.createVitaldaten(patientId,vitaldaten);
        Optional<Vitaldaten> found = repository.getVitaldatenById(patientId);
        assertTrue(found.isPresent());
        assertEquals(vitaldaten, found.get());
    }

     */
}