package com.bht.MediTrack.Behandlungsmanagement.application.services;

import com.bht.MediTrack.Behandlungsmanagement.domain.events.BehandlungErstelltEvent;
import com.bht.MediTrack.Behandlungsmanagement.domain.model.Behandlung;
import com.bht.MediTrack.Behandlungsmanagement.infrastructure.repositories.BehandlungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


import java.util.*;

    @Service
    public class Behandlungsmanagementservice {

        @Autowired
        private  BehandlungRepository behandlungRepository;
        private final ApplicationEventPublisher eventPublisher;

        public Behandlungsmanagementservice(BehandlungRepository behandlungRepository, ApplicationEventPublisher eventPublisher) {
            this.behandlungRepository = Objects.requireNonNull(behandlungRepository, "Repository darf nicht null sein");
            this.eventPublisher = eventPublisher;
        }


        public Behandlung createBehandlung(Behandlung behandlung) {

            behandlung.setId(UUID.randomUUID());

            Behandlung gespeicherteBehandlung = behandlungRepository.save(behandlung);

            BehandlungErstelltEvent event = new BehandlungErstelltEvent(
                    gespeicherteBehandlung.getId(),
                    gespeicherteBehandlung.getBeschreibung(),
                    gespeicherteBehandlung.getPatient(),
                    gespeicherteBehandlung.getArzt()
            );

            eventPublisher.publishEvent(event);

            return gespeicherteBehandlung;
        }

        // Methode zum Abrufen aller Behandlungen für einen bestimmten Patienten
        public List<Behandlung> getBehandlungenByPatientId(UUID patientId) {
            return behandlungRepository.getBehandlungenByPatientId(patientId);
        }

    //Methode zum Aktualisieren der Beschreibung einer Behandlung
    /*public void updateBehandlung(String beschreibung, UUID id) {
        Behandlung behandlung = behandlungMap.get(id);
        if (behandlung != null) {
            behandlung.setBeschreibung(beschreibung);
            behandlungMap.put(id, behandlung);
        } else {
            throw new IllegalArgumentException("Behandlung mit der ID " + id + " nicht gefunden.");
        }
        BehandlungUpdateEvent event = new BehandlungUpdateEvent(
                behandlung.getId(),
                beschreibung
        );
        eventListener.publishEvent(event);
    }

    //Methode zum Löschen einer Behandlung
    public void deleteBehandlung(UUID id) {
        if (behandlungMap.containsKey(id)) {
            behandlungMap.remove(id);
        } else {
            throw new IllegalArgumentException("Behandlung mit der ID " + id + " nicht gefunden.");
        }
    }

     */
}

