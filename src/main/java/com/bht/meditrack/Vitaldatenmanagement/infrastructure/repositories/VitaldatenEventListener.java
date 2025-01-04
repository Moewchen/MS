package com.bht.meditrack.Vitaldatenmanagement.infrastructure.repositories;

import com.bht.meditrack.Vitaldatenmanagement.domain.events.VitaldatenErstelltEvent;
import com.bht.meditrack.Vitaldatenmanagement.domain.events.VitaldatenUpdateEvent;
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
    public void handleVitaldatenUpdateEvent(VitaldatenUpdateEvent event) {
        log.info("Vitaldaten update ID={}, Herzfrequenz={}, Atemfrequenz={}, Systolisch={}, Diastolisch={}, Temperatur={}, Datum={}",
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
