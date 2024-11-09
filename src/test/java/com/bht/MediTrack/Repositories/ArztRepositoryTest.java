package com.bht.MediTrack.Repositories;

import com.bht.MediTrack.Entities.Arzt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArztRepositoryTest {

    private ArztRepository arztRepository;

    @BeforeEach
    void setUp() {
        arztRepository = new ArztRepository();
    }

    @Test
    public void TestFindArztById() {
        Arzt arzt = new Arzt(
                "Allgemeinmedizin",
                "Tom",
                "Müller",
                "Dr. Med.",
                LocalDate.of(1976,3,1),
                "müller@arzt.de",
                "015050505",
                "Hauptdamm 22, 012345 Berlin");
        Arzt savedArzt = arztRepository.save(arzt);
        Optional<Arzt> foundArzt = arztRepository.findArztById(savedArzt.getId());

        assertThat(foundArzt).isPresent();
        assertThat(foundArzt.get().getFirstName()).isEqualTo("Tom");
    }
}