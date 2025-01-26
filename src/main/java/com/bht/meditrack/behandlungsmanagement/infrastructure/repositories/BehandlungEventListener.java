package com.bht.meditrack.behandlungsmanagement.infrastructure.repositories;

import com.bht.meditrack.behandlungsmanagement.domain.events.BehandlungErstelltEvent;
import com.bht.meditrack.behandlungsmanagement.domain.events.BehandlungUpdateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BehandlungEventListener {

    @EventListener
    public void handleBehandlungErstelltEvent(BehandlungErstelltEvent event) {
        log.info("Behandlung Erstellt: ID={}, Beschreibung={}",
                event.getId(),
                event.getBeschreibung()
        );
    }

    public void handleBehandlungUpdateEvent(BehandlungUpdateEvent event) {
        log.info("Behandlung Update: ID={}, Beschreibung={}",
                event.getId(),
                event.getBeschreibung()
        );
    }
}
