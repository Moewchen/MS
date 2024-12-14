package com.bht.MediTrack.Vitaldatenmanagement.application.services;

import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import com.bht.MediTrack.Vitaldatenmanagement.infrastructure.repositories.VitaldatenRepository;
import com.bht.MediTrack.shared.domain.valueobjects.Personendaten;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class VitaldatenServiceIntegrationTest {

    /*

    @Autowired
    private VitaldatenService vitaldatenService;

    @Autowired
    private VitaldatenRepository vitaldatenRepository;

    @Test
    void testInsertVitaldatenIntegration() {
        Vitaldaten vitaldaten = new Vitaldaten();
        vitaldaten.setId(UUID.randomUUID());
        vitaldaten.setHerzfrequenz((short) 72);
        vitaldaten.setTemperatur(37.0f);
        vitaldaten.setDatum(LocalDateTime.now());

        Vitaldaten saved = vitaldatenRepository.save(vitaldaten);

        assertNotNull(saved);
        assertEquals(72, saved.getHerzfrequenz());
    }

    @Test
    void testInsertVitaldatenIntegration2() {
        // Setup example data
        Vitaldaten newVitaldaten = new Vitaldaten();
        newVitaldaten.setId(UUID.randomUUID());
        newVitaldaten.setHerzfrequenz((short) 72);
        newVitaldaten.setAtemfrequenz((byte) 18);
        newVitaldaten.setSystolisch((short) 120);
        newVitaldaten.setDiastolisch((short) 80);
        newVitaldaten.setTemperatur(37.0f);
        newVitaldaten.setDatum(LocalDateTime.now());

        Vitaldaten savedVitaldaten = vitaldatenRepository.save(newVitaldaten);
        assertNotNull(savedVitaldaten);
        assertEquals(newVitaldaten.getId(), savedVitaldaten.getId());
    }

    @Test
    void testUpdateVitaldatenIntegration() {
        // Insert a test record
        Vitaldaten existingVitaldaten = new Vitaldaten();
        existingVitaldaten.setId(UUID.randomUUID());
        existingVitaldaten.setHerzfrequenz((short) 70);
        existingVitaldaten.setAtemfrequenz((byte) 20);
        existingVitaldaten.setSystolisch((short) 110);
        existingVitaldaten.setDiastolisch((short) 70);
        existingVitaldaten.setTemperatur(36.5f);
        existingVitaldaten.setDatum(LocalDateTime.now());

        // Set the patient field
        Patient patient = new Patient();
        patient.setId(UUID.randomUUID());

        // Create and set Personendaten
        Personendaten personendaten = new Personendaten("John", "Doe", "123456789", LocalDate.of(1990, 1, 1));
        patient.setPersonendaten(personendaten);

        existingVitaldaten.setPatient(patient);

        Vitaldaten savedVitaldaten = vitaldatenRepository.save(existingVitaldaten);


        // Update the record
        savedVitaldaten.setHerzfrequenz((short) 80);
        Vitaldaten updatedVitaldaten = vitaldatenRepository.save(savedVitaldaten);

        assertEquals(80, updatedVitaldaten.getHerzfrequenz());
        assertEquals(savedVitaldaten.getId(), updatedVitaldaten.getId());
    }

    @Test
    void testDeleteVitaldatenIntegration() {
        // Insert test data
        Vitaldaten toBeDeleted = new Vitaldaten();
        toBeDeleted.setId(UUID.randomUUID());
        toBeDeleted.setHerzfrequenz((short) 65);
        toBeDeleted.setAtemfrequenz((byte) 15);
        toBeDeleted.setSystolisch((short) 100);
        toBeDeleted.setDiastolisch((short) 70);
        toBeDeleted.setTemperatur(36.2f);
        toBeDeleted.setDatum(LocalDateTime.now());
        vitaldatenRepository.save(toBeDeleted);

        // Delete the inserted data
        vitaldatenRepository.deleteById(toBeDeleted.getId());

        Optional<Vitaldaten> deletedVitaldaten = vitaldatenRepository.findById(toBeDeleted.getId());
        assertFalse(deletedVitaldaten.isPresent());
    }

     */
}