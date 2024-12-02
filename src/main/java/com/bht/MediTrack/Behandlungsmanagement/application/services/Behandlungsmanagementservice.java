package com.bht.MediTrack.Behandlungsmanagement.application.services;

import com.bht.MediTrack.Behandlungsmanagement.domain.events.BehandlungErstelltEvent;
import com.bht.MediTrack.Behandlungsmanagement.domain.events.BehandlungUpdateEvent;
import com.bht.MediTrack.Behandlungsmanagement.domain.model.Behandlung;
import com.bht.MediTrack.PublisherEvent;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class Behandlungsmanagementservice {

    private final Map<UUID, Behandlung> behandlungMap = new HashMap<>();
    private final PublisherEvent eventListener;

    public Behandlungsmanagementservice(PublisherEvent eventListener) {
        this.eventListener = eventListener;
    }

    //Methode zum Erstellen einer neuen Behandlung
    public Behandlung createBehandlung(Behandlung behandlung) {
        behandlung.setId(UUID.randomUUID());
        behandlungMap.put(behandlung.getId(), behandlung);

        BehandlungErstelltEvent event = new BehandlungErstelltEvent(
                behandlung.getId(),
                behandlung.getBeschreibung(),
                behandlung.getPatient(),
                behandlung.getArzt(),
                behandlung.getVitaldaten()
        );
        eventListener.publishEvent(event);

        return behandlung;
    }

    //Methode zum Abrufen aller Behandlungen für einen bestimmten Patienten
    public List<Behandlung> getBehandlungenByPatientId(UUID patientId) {
        return behandlungMap.values().stream()
                .filter(behandlung -> patientId.equals(behandlung.getPatientId()))
                .collect(Collectors.toList());
    }

    //Methode zum Aktualisieren der Beschreibung einer Behandlung
    public void updateBehandlung(String beschreibung, UUID id) {
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
}
