package com.bht.meditrack.arztverwaltung.application.services;

import com.bht.meditrack.arztverwaltung.domain.model.Arzt;
import com.bht.meditrack.arztverwaltung.infrastructure.repositories.ArztRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArztService {

    private final ArztRepository arztRepository;
    final ApplicationEventPublisher eventPublisher;

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
        return arztRepository.findAll();
    }

    public Arzt findArztById(UUID arztId){
        return arztRepository.findById(arztId).orElse(null);
    }

}
