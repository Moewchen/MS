package com.bht.MediTrack.Arztverwaltung.application.services;

import com.bht.MediTrack.Arztverwaltung.domain.model.Arzt;
import com.bht.MediTrack.Arztverwaltung.infrastructure.repositories.ArztRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.*;

public class ArztService { ;

    //@Autowired
    private final ArztRepository arztRepository;
    private final ApplicationEventPublisher eventPublisher;

    //@Autowired
    public ArztService(ArztRepository arztRepository, ApplicationEventPublisher eventPublisher) {
        this.arztRepository = Objects.requireNonNull(arztRepository,"Repository darf nicht null sein.");
        this.eventPublisher = eventPublisher;
    }

    public Arzt upsertArzt(UUID arztId, final Arzt arzt){
        if (arztId == null) {
            throw new IllegalArgumentException("ArztId darf nicht null sein.");
        }
        if (arzt == null) {
            throw new IllegalArgumentException("Arzt darf nicht null sein.");
        }
        arzt.setId(arztId);
        return arztRepository.save(arzt);
    }

    public List<Arzt> getAllAerzte(){
        return (List<Arzt>) arztRepository.findAll();
    }

    public Arzt findArztById(UUID arztId){
        return arztRepository.findById(arztId).orElse(null);
    }

     /*
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
      */
}
