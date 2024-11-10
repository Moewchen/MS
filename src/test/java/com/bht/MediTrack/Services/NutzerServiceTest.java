package com.bht.MediTrack.Services;
import com.bht.MediTrack.Entities.Nutzer;
import com.bht.MediTrack.Repositories.InMemoryNutzerRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class NutzerServiceTest {

    private InMemoryNutzerRepository repository;
    private NutzerService service;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryNutzerRepository();
        service = new NutzerService(repository);
    }

    @Test
    public void testCreateNutzer() {
        Nutzer nutzer = service.createNutzer("Max", "Muster", "Dr.", LocalDate.of(1985, 5, 20), "+491234567890", "max@example.com", "Musterstraße 1");
        nutzer.setId(UUID.randomUUID());

        assertNotNull(nutzer.getId(), "Nutzer-ID sollte nicht null sein");
        assertEquals("Max", nutzer.getFirstName());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testFindNutzerById() {
        Nutzer nutzer = service.createNutzer("Anna", "Musterfrau", "Prof.", LocalDate.of(1990, 2, 10), "+4912345678", "anna@example.com", "Hauptstraße 2");
        Optional<Nutzer> foundNutzer = service.findNutzerById(nutzer.getId());
        assertTrue(foundNutzer.isPresent());
        assertEquals("Anna", foundNutzer.get().getFirstName());
    }

    @Test
    public void testCreateNutzerInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> service.createNutzer("Max", "Mustermann", "Dr.", LocalDate.of(2001, 5, 20), "+491234567890", "invalid-email", "Musterstr. 1"));
    }

    @Test
    public void testFindNutzerByNameEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> service.findNutzerByName(""), "Name darf nicht leer sein.");
    }

    @Test
    public void testFindNutzerByEmail() {
        service.createNutzer("Tom", "Mustermann", "Dr.", LocalDate.of(1988, 7, 15), "+49987654321", "tom@example.com", "Nebenstraße 3");

        Optional<Nutzer> foundNutzer = service.findNutzerByEmail("tom@example.com");
        assertTrue(foundNutzer.isPresent());
        assertEquals("Tom", foundNutzer.get().getFirstName());
    }

    @Test
    public void testFindNutzerByName() {
        service.createNutzer("Max", "Mustermann", "Dr.", LocalDate.of(1985, 5, 20), "+491234567890", "max@example.com", "Musterstraße 1");
        service.createNutzer("Anna", "Mustermann", "Dr.", LocalDate.of(1990, 8, 12), "+4912345678", "anna@example.com", "Hauptstraße 2");

        List<Nutzer> foundNutzer = service.findNutzerByName("Anna");
        assertEquals(1, foundNutzer.size());
        assertEquals("Anna", foundNutzer.getFirst().getFirstName());
    }

    @Test
    public void testFindNutzerBornBefore() {
        // Erstelle zwei Nutzer
        Nutzer nutzer1 = service.createNutzer("Lisa", "Klein", "Ms.", LocalDate.of(1985, 3, 5), "+4912345678", "lisa@example.com", "Blumenstraße 4");
        nutzer1.setId(UUID.randomUUID());
        repository.save(nutzer1);

        Nutzer nutzer2 = service.createNutzer("John", "Doe", "Mr.", LocalDate.of(1995, 6, 1), "+4912345678", "john@example.com", "Parkstraße 6");
        nutzer2.setId(UUID.randomUUID());
        repository.save(nutzer2);

        // Suche alle Nutzer, die vor 1990 geboren wurden
        List<Nutzer> foundNutzer = service.findNutzerBornBefore(1990);

        // Prüfe, dass nur ein Nutzer zurückgegeben wird
        assertEquals(1, foundNutzer.size());

        // Überprüfe, dass der zurückgegebene Nutzer der richtige ist (Lisa)
        assertEquals("Lisa", foundNutzer.getFirst().getFirstName());
        assertEquals("Klein", foundNutzer.getFirst().getLastName());
    }

    @Test
    public void testEmailExists() {
        service.createNutzer("Emma", "Schulz", "Ms.", LocalDate.of(2019, 11, 1), "+4912345678", "emma@example.com", "Parkstraße 6");

        assertTrue(service.emailExists("emma@example.com"));
        assertFalse(service.emailExists("nonexistentexample.com"));
    }

    @Test
    public void testCreateNutzer_InvalidEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createNutzer("Invalid", "Email", "Mr.", LocalDate.of(1980, 1, 1), "+491234567890", "invalid-email", "Street 2"));
    }


    @Test
    public void testDeleteNutzer() {
        Nutzer nutzer = service.createNutzer("John", "Doe", "Mr.", LocalDate.of(1991, 4, 21), "+4912345678", "john@example.com", "Ringstraße 7");
        service.deleteNutzer(nutzer.getId());
        assertFalse(repository.findById(nutzer.getId()).isPresent());
    }


    @Test
    public void testDeleteNutzerSuccess() {
        Nutzer nutzer = service.createNutzer("ToDelete", "User", "Mr.", LocalDate.of(2018, 1, 1), "+491234567890", "delete@example.com", "Delete Street");
        UUID nutzerId = nutzer.getId();
        service.deleteNutzer(nutzerId);
        assertFalse(repository.findById(nutzerId).isPresent());
    }

    @Test
    public void testDeleteNutzerNotFound() {
        UUID randomId = UUID.randomUUID();
        assertThrows(NoSuchElementException.class, () -> service.deleteNutzer(randomId));
    }

    @Test
    public void testDeleteAllNutzer() {
        service.createNutzer("Emma", "Schulz", "Ms.", LocalDate.of(1995, 6, 1), "+4912345678", "emma@example.com", "Parkstraße 6");
        service.createNutzer("John", "Doe", "Mr.", LocalDate.of(1991, 4, 21), "+4912345678", "john@example.com", "Ringstraße 7");

        service.deleteAllNutzer();
        assertEquals(0, repository.findAll().size());
    }
}
