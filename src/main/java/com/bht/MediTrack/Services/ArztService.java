package com.bht.MediTrack.Services;

import com.bht.MediTrack.Entities.Arzt;
import com.bht.MediTrack.Repositories.ArztRepository;

import java.util.*;

public class ArztService { ;

    private final ArztRepository arztRepository;

    public ArztService(ArztRepository arztRepository){
        this.arztRepository = arztRepository;
    }

    public Optional<Arzt> findArztById(UUID id){
        if (id == null) {
            throw new IllegalArgumentException("ID darf nicht null sein.");
        }
        return arztRepository.findArztById(id);
    }

    public List<Arzt> getArztByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht null oder leer sein.");
        }
        return arztRepository.findArztByName(name);
    }

    public List<Arzt> getArztByFachrichtung(String fachrichtung) {
        if (fachrichtung == null || fachrichtung.trim().isEmpty()) {
            throw new IllegalArgumentException("Fachrichtung darf nicht null oder leer sein.");
        }
        return arztRepository.findArztByFachrichtung(fachrichtung);
    }
}
