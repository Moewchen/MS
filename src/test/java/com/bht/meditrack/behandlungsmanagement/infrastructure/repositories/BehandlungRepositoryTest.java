package com.bht.meditrack.behandlungsmanagement.infrastructure.repositories;

import com.bht.meditrack.behandlungsmanagement.domain.model.Behandlung;
import com.bht.meditrack.patientenverwaltung.domain.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import java.util.UUID;

class BehandlungRepositoryTest {

    private BehandlungRepository behandlungRepository;
    private Behandlung behandlung;
    @BeforeEach
    public void setUp() {
        behandlungRepository = new BehandlungRepository();
        Patient patient = new Patient();
        patient.setId(UUID.randomUUID());
        behandlung = new Behandlung(UUID.randomUUID(), "Initial Beschreibung", patient, null);
    }
    @Test
     void testCreateBehandlung() {
        //Testen der Erstellung einer neuen Behandlung
        Behandlung createdBehandlung = behandlungRepository.createBehandlung(behandlung);
        assertNotNull(createdBehandlung.getId(), "ID sollte nicht null sein.");
        assertEquals(behandlung.getBeschreibung(), createdBehandlung.getBeschreibung(), "Die Beschreibung sollte übereinstimmen.");
        assertTrue(behandlungRepository.findById(createdBehandlung.getId()).isPresent(), "Behandlung sollte im Repository vorhanden sein.");
    }
    @Test
     void testFindById() {
        //Testen Behandlung aufrufen
        behandlungRepository.createBehandlung(behandlung);
        Optional<Behandlung> foundBehandlung = behandlungRepository.findById(behandlung.getId());
        assertTrue(foundBehandlung.isPresent(), "Behandlung sollte gefunden werden.");
        assertEquals(behandlung.getBeschreibung(), foundBehandlung.get().getBeschreibung(), "Die Beschreibung sollte übereinstimmen.");
    }
    @Test
     void testUpdateBehandlung() {
        // esten der Aktualisierung einer bestehenden Behandlung
        behandlungRepository.createBehandlung(behandlung);
        String neueBeschreibung = "Aktualisierte Beschreibung";
        behandlungRepository.updateBehandlung(neueBeschreibung, behandlung.getId());
        Optional<Behandlung> updatedBehandlung = behandlungRepository.findById(behandlung.getId());
        assertTrue(updatedBehandlung.isPresent(), "Behandlung sollte nach der Aktualisierung vorhanden sein.");
        assertEquals(neueBeschreibung, updatedBehandlung.get().getBeschreibung(), "Die Beschreibung sollte aktualisiert worden sein.");
    }
}
