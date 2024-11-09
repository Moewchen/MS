package com.bht.MediTrack.Repositories;
import com.bht.MediTrack.Entities.Nutzer;
import com.bht.MediTrack.Entities.Patient;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryNutzerRepository {
    private final Map<UUID, Nutzer> datastore = new HashMap<>();

    // Nutzer speichern oder aktualisieren
    public void save(Nutzer nutzer) {
        datastore.put(nutzer.getId(), nutzer);
    }

    // Nutzer nach ID finden
    public Optional<Nutzer> findById(UUID id) {
        return Optional.ofNullable(datastore.get(id));
    }

    // Alle Nutzer abrufen
    public List<Nutzer> findAll() {
        return new ArrayList<>(datastore.values());
    }

    // Nutzer nach E-Mail finden
    public Optional<Nutzer> findByEmail(String email) {
        return datastore.values().stream()
                .filter(n -> n.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // Nutzer nach Kriterien filtern
    public List<Nutzer> findAll(Predicate<Nutzer> filter) {
        return datastore.values().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    // Nutzer löschen
    public void deleteById(UUID id) {
        datastore.remove(id);
    }

    // Löschen aller Nutzer
    public void deleteAll() {
        datastore.clear();
    }
}
