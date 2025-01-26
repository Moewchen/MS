package com.bht.meditrack.vitaldatenmanagement.infrastructure.repositories;

import com.bht.meditrack.patientenverwaltung.infrastructure.persistence.PatientEntity;
import com.bht.meditrack.vitaldatenmanagement.infrastructure.persistence.VitaldatenEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("VitaldatenRepository Tests")
class VitaldatenRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VitaldatenRepository vitaldatenRepository;

    private PatientEntity testPatient;
    private VitaldatenEntity testVitaldaten1;
    private VitaldatenEntity testVitaldaten2;

    @BeforeEach
    void setUp() {
        testPatient = new PatientEntity();
        testPatient = entityManager.persist(testPatient);

        testVitaldaten1 = createTestVitaldaten(testPatient, (short) 75, (byte) 16);
        testVitaldaten2 = createTestVitaldaten(testPatient, (short) 80, (byte) 18);

        entityManager.persist(testVitaldaten1);
        entityManager.persist(testVitaldaten2);
        entityManager.flush();
    }

    private VitaldatenEntity createTestVitaldaten(PatientEntity patient, short herzfrequenz, byte atemfrequenz) {
        VitaldatenEntity vitaldaten = new VitaldatenEntity();
        vitaldaten.setPatient(patient);
        vitaldaten.setHerzfrequenz(herzfrequenz);
        vitaldaten.setAtemfrequenz(atemfrequenz);
        vitaldaten.setSystolisch((short) 120);
        vitaldaten.setDiastolisch((short) 80);
        vitaldaten.setTemperatur(37.0f);
        vitaldaten.setDatum(LocalDateTime.now());
        return vitaldaten;
    }

    @Test
    @DisplayName("Should find vitaldaten by patient ID")
    void shouldFindVitaldatenByPatientId() {
        List<VitaldatenEntity> found = vitaldatenRepository.findByPatientId(testPatient.getId());

        assertThat(found).hasSize(2)
                .extracting(VitaldatenEntity::getHerzfrequenz)
                .containsExactlyInAnyOrder((short) 75, (short) 80);
    }

    @Test
    @DisplayName("Should return empty list for non-existent patient ID")
    void shouldReturnEmptyListForNonExistentPatientId() {
        List<VitaldatenEntity> found = vitaldatenRepository.findByPatientId(UUID.randomUUID());

        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Should not delete vitaldaten with wrong patient ID")
    void shouldNotDeleteVitaldatenWithWrongPatientId() {
        vitaldatenRepository.deleteByPatientIdAndId(UUID.randomUUID(), testVitaldaten1.getId());
        entityManager.clear();

        List<VitaldatenEntity> remaining = vitaldatenRepository.findByPatientId(testPatient.getId());

        assertThat(remaining).hasSize(2);
    }

    @Test
    @DisplayName("Should save vitaldaten with patient reference")
    void shouldSaveVitaldatenWithPatientReference() {
        PatientEntity newPatient = entityManager.persist(new PatientEntity());

        VitaldatenEntity newVitaldaten = createTestVitaldaten(newPatient, (short) 90, (byte) 20);
        vitaldatenRepository.save(newVitaldaten);
        entityManager.flush();
        entityManager.clear();

        List<VitaldatenEntity> found = vitaldatenRepository.findByPatientId(newPatient.getId());
        assertThat(found).hasSize(1)
                .extracting(VitaldatenEntity::getHerzfrequenz)
                .containsExactly((short) 90);
    }
}