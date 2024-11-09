package com.bht.MediTrack.Repositories;

import com.bht.MediTrack.Entities.Arzt;
import com.bht.MediTrack.Entities.Patient;
import org.springframework.data.repository.Repository;

import java.util.*;
import java.util.stream.Collectors;

public class ArztRepository{

    private final Map<UUID, Arzt> arztStorage = new HashMap<>();

    // Neuen Arzt speichern
    public Arzt save(Arzt arzt) {
        if (arzt.getId() == null) {
            arzt.setId(UUID.randomUUID());
        }
        arztStorage.put(arzt.getId(), arzt);
        return arzt;
    }

    public Optional<Arzt> findArztById(UUID id) {
        return Optional.ofNullable(arztStorage.get(id));
    }

    public List<Arzt> findArztByName(String name) {
        return arztStorage.values().stream()
                .filter(arzt -> arzt.getFirstName().equalsIgnoreCase(name) ||
                        arzt.getLastName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Arzt> findArztByFachrichtung(String fachrichtung) {
        return arztStorage.values().stream()
                .filter(arzt -> arzt.getFachrichtung().equalsIgnoreCase(fachrichtung))
                .collect(Collectors.toList());
    }

}
