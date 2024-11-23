package com.bht.MediTrack.Arztverwaltung.infrastructure.repositories;

import com.bht.MediTrack.Arztverwaltung.domain.model.Arzt;

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
                .filter(arzt -> arzt.getPersonendaten().firstName().equalsIgnoreCase(name) ||
                        arzt.getPersonendaten().lastName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Arzt> findArztByFachrichtung(String fachrichtung) {
        return arztStorage.values().stream()
                .filter(arzt -> arzt.getFachrichtung().fachrichtung().equalsIgnoreCase(fachrichtung))
                .collect(Collectors.toList());
    }

}
