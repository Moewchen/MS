package com.bht.MediTrack.Vitaldatenmanagement.infrastructure.repositories;

import com.bht.MediTrack.Vitaldatenmanagement.domain.events.VitaldatenErstelltEvent;
import com.bht.MediTrack.Vitaldatenmanagement.domain.events.VitaldatenUpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class VitaldatenEventListener {

    @EventListener
    public void handleVitaldatenErstelltEvent(VitaldatenErstelltEvent event) {
        System.out.println("Vitaldaten erstellt ID=" + event.getId() + ", Herzfrequenz=" + event.getHerzfrequenz() + ", Atemfrequenz=" + event.getAtemfrequenz() + ", Systolisch=" + event.getSystolisch() + ", Diastolisch=" + event.getDiastolisch() + ", Temperatur=" + event.getTemperatur() + ", Datum=" + event.getDatum());
    }
    public void handleVitaldatenUpdateEvent(VitaldatenUpdateEvent event) {
        System.out.println("Vitaldaten update ID=" + event.getId() + ", Herzfrequenz=" + event.getHerzfrequenz() + ", Atemfrequenz=" + event.getAtemfrequenz() + ", Systolisch=" + event.getSystolisch() + ", Diastolisch=" + event.getDiastolisch() + ", Temperatur=" + event.getTemperatur() + ", Datum=" + event.getDatum());
    }
}
