package com.bht.meditrack.behandlungsmanagement.application.services;

import com.bht.meditrack.behandlungsmanagement.domain.events.BehandlungErstelltEvent;
import com.bht.meditrack.behandlungsmanagement.domain.model.Behandlung;
import com.bht.meditrack.behandlungsmanagement.infrastructure.repositories.BehandlungRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


import java.util.*;

    @Service
    public class Behandlungsmanagementservice {

        private final BehandlungRepository behandlungRepository;
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
}

