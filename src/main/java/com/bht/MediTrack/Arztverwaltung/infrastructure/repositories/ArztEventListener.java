package com.bht.MediTrack.Arztverwaltung.infrastructure.repositories;

import com.bht.MediTrack.Arztverwaltung.domain.events.ArztAngelegtEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ArztEventListener {
    @EventListener
    public void handleArztAngelegt(ArztAngelegtEvent event) {
        System.out.println(
        "Neuen Arzt angelegt: " + event.getArztId() +
        ", Fachrichtung =" + event.getArztFachrichtung() +
        ", Personendaten =" + event.getArztPersonendaten() +
        ", Kontaktdaten =" + event.getArztKontaktdaten() +
        ", Adresse =" + event.getArztAdresse() +
        ", Angelegt am =" + event.getArztCreatedAt());
    }
}
