package com.bht.MediTrack.Services;
import com.bht.MediTrack.Repositories.InMemoryNutzerRepository;
import com.bht.MediTrack.Entities.Nutzer;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class NutzerService {

    private final InMemoryNutzerRepository repository;

    public NutzerService(InMemoryNutzerRepository repository) {
        this.repository = repository;
    }

    // Neuen Nutzer erstellen und speichern
    public Nutzer createNutzer(String firstName, String lastName, String titel, LocalDate dateOfBirth, String telefon, String email, String adresse) {
        Nutzer nutzer = new Nutzer(firstName, lastName, titel, dateOfBirth, telefon, email, adresse);
        repository.save(nutzer);
        return nutzer;
    }

    // Nutzer nach ID finden
    public Optional<Nutzer> findNutzerById(UUID id) {
        return repository.findById(id);
    }

    // Nutzer nach E-Mail suchen
    public Optional<Nutzer> findNutzerByEmail(String email) {
        return repository.findByEmail(email);
    }

    // Alle Nutzer abrufen
    public List<Nutzer> getAllNutzer() {
        return repository.findAll();
    }

    // Nutzer nach Namen filtern
    public List<Nutzer> findNutzerByName(String name) {
        return repository.findAll(n -> n.getFirstName().equalsIgnoreCase(name) || n.getLastName().equalsIgnoreCase(name));
    }

    // Alle Nutzer mit einem bestimmten Titel finden
    public List<Nutzer> findNutzerByTitel(String titel) {
        return repository.findAll(n -> n.getTitel().equalsIgnoreCase(titel));
    }

    // Nutzer löschen
    public void deleteNutzer(UUID id) {
        repository.deleteById(id);
    }

    // Alle Nutzer löschen
    public void deleteAllNutzer() {
        repository.deleteAll();
    }

    // Alle Nutzer abrufen, die vor einem bestimmten Jahr geboren sind
    public List<Nutzer> findNutzerBornBefore(int year) {
       // return repository.findAll(n -> n.getDateOfBirth().getYear() < year);
        return repository.findAll().stream()
                .filter(n -> n.getDateOfBirth() != null && n.getDateOfBirth().getYear() < year)
                .collect(Collectors.toList());
    }

    // Nutzer nach E-Mail überprüfen und validieren
    public boolean emailExists(String email) {
        return repository.findByEmail(email).isPresent();
    }
}
