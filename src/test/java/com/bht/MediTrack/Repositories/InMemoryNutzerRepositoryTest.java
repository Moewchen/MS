package com.bht.MediTrack.Repositories;
import com.bht.MediTrack.Entities.Nutzer;
import com.bht.MediTrack.Repositories.InMemoryNutzerRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryNutzerRepositoryTest {

    private InMemoryNutzerRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryNutzerRepository();
    }

    @Test
    public void testSaveAndFindById() {
        Nutzer nutzer = new Nutzer("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20), "+491234567890", "max@example.com", "Musterstraße 1");
        repository.save(nutzer);

        Optional<Nutzer> retrievedNutzer = repository.findById(nutzer.getId());
        assertTrue(retrievedNutzer.isPresent());
        assertEquals("Max", retrievedNutzer.get().getFirstName());
    }

    @Test
    public void testFindAll() {
        Nutzer nutzer1 = new Nutzer("Anna", "Schmidt", "Prof.", LocalDate.of(1990, 2, 10), "+4912345678", "anna@example.com", "Hauptstraße 2");
        Nutzer nutzer2 = new Nutzer("Tom", "Anders", "Dr.", LocalDate.of(1988, 7, 15), "+49987654321", "tom@example.com", "Nebenstraße 3");
        nutzer1.setId(UUID.randomUUID());
        nutzer2.setId(UUID.randomUUID());
        repository.save(nutzer1);
        repository.save(nutzer2);

        List<Nutzer> allNutzer = repository.findAll();

        assertEquals(2, allNutzer.size());
        assertTrue(allNutzer.stream().anyMatch(n -> n.getFirstName().equals("Anna")));
        assertTrue(allNutzer.stream().anyMatch(n -> n.getFirstName().equals("Tom")));
    }

    @Test
    public void testFindByEmail() {
        Nutzer nutzer = new Nutzer("Lisa", "Klein", "Dr.", LocalDate.of(1985, 3, 5), "+4912345678", "lisa@example.com", "Blumenstraße 4");
        repository.save(nutzer);

        Optional<Nutzer> retrievedNutzer = repository.findByEmail("lisa@example.com");
        assertTrue(retrievedNutzer.isPresent());
        assertEquals("Lisa", retrievedNutzer.get().getFirstName());
    }

    @Test
    public void testDeleteById() {
        Nutzer nutzer = new Nutzer("Peter", "Müller", "Mr.", LocalDate.of(1978, 11, 20), "+4912345678", "peter@example.com", "Allee 5");
        repository.save(nutzer);

        repository.deleteById(nutzer.getId());
        assertFalse(repository.findById(nutzer.getId()).isPresent());
    }

    @Test
    public void testDeleteAll() {
        Nutzer nutzer1 = new Nutzer("Emma", "Schulz", "Ms.", LocalDate.of(1995, 6, 1), "+4912345678", "emma@example.com", "Parkstraße 6");
        Nutzer nutzer2 = new Nutzer("John", "Doe", "Mr.", LocalDate.of(1991, 4, 21), "+4912345678", "john@example.com", "Ringstraße 7");
        repository.save(nutzer1);
        repository.save(nutzer2);

        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }
}
