package com.bht.meditrack.Arztverwaltung.infrastructure.repositories;

import com.bht.meditrack.Arztverwaltung.domain.events.ArztAngelegtEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ArztEventListener {
    private static final Logger logger = LoggerFactory.getLogger(ArztEventListener.class);

    @EventListener
    public void handleArztAngelegt(ArztAngelegtEvent event) {
        logger.info("Neuen Arzt angelegt: ID={}, Fachrichtung={}, Personendaten={}, " +
                        "Kontaktdaten={}, Adresse={}, Angelegt am={}",
                event.getArztId(),
                event.getFachrichtung(),
                event.getPersonendaten(),
                event.getKontaktdaten(),
                event.getAdresse(),
                event.getCreatedAt());
    }
}
