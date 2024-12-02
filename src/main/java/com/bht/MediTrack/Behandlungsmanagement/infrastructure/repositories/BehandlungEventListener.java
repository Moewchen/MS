package com.bht.MediTrack.Behandlungsmanagement.infrastructure.repositories;

import com.bht.MediTrack.Behandlungsmanagement.domain.events.BehandlungErstelltEvent;
import com.bht.MediTrack.Behandlungsmanagement.domain.events.BehandlungUpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BehandlungEventListener {

    @EventListener
    public void handleBehandlungErstelltEvent(BehandlungErstelltEvent event) {
        System.out.println("Behandlung Erstellt=" + event.getId() + ", Beschreibung=" + event.getBeschreibung());
    }

    public void handleBehandlungUpdateEvent(BehandlungUpdateEvent event) {
        System.out.println("Behandlung Update=" + event.getId() + ", Beschreibung=" + event.getBeschreibung());
    }
}
