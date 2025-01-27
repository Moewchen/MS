package com.bht.meditrack.vitaldatenmanagement.infrastructure.repositories;

import com.bht.meditrack.vitaldatenmanagement.domain.events.VitaldatenErstelltEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VitaldatenEventListener {

    @EventListener
    public void handleVitaldatenErstelltEvent(VitaldatenErstelltEvent event) {
        log.info("Vitaldaten erstellt ID={}, Herzfrequenz={}, Atemfrequenz={}, Systolisch={}, Diastolisch={}, Temperatur={}, Datum={}",
                event.getId(),
                event.getHerzfrequenz(),
                event.getAtemfrequenz(),
                event.getSystolisch(),
                event.getDiastolisch(),
                event.getTemperatur(),
                event.getDatum()
        );
    }
}
