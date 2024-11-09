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
        return arztRepository.findArztById(id);
    }

    public List<Arzt> getArztByName(String name) {
        return arztRepository.findArztByName(name);
    }

    public List<Arzt> getArztByFachrichtung(String fachrichtung) {
        return arztRepository.findArztByFachrichtung(fachrichtung);
    }
}
