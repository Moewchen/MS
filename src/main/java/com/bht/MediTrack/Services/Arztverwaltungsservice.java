package com.bht.MediTrack.Services;

import com.bht.MediTrack.Entities.Arzt;
import com.bht.MediTrack.Repositories.ArztRepository;
import java.util.List;
import java.util.UUID;

public class Arztverwaltungsservice {

    private final ArztRepository arztRepository;

    public Arztverwaltungsservice(ArztRepository arztRepository){
        this.arztRepository = arztRepository;
    }

    public Arzt getArztById(UUID ArztId){
        return arztRepository.findArztById(ArztId).orElse(null);
    }

    public List<Arzt> getArztByName(String firstName, String lastname) {
        return arztRepository.findArztByName(firstName, lastname);
    }

    public List<Arzt> getArztByFachrichtung(String fachrichtung) {
        return arztRepository.findArztByFachrichtung(fachrichtung);
    }
}