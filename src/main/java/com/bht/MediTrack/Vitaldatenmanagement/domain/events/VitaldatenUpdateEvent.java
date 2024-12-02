package com.bht.MediTrack.Vitaldatenmanagement.domain.events;

import java.time.LocalDateTime;
import java.util.UUID;

public class VitaldatenUpdateEvent {

    private final UUID id; // Die eindeutige ID der Vitaldaten
    private final short herzfrequenz;
    private final byte atemfrequenz;
    private final short systolisch;
    private final short diastolisch;
    private final float temperatur;
    private final LocalDateTime datum;


    public VitaldatenUpdateEvent(UUID id, short herzfrequenz, byte atemfrequenz, short systolisch, short diastolisch, float temperatur, LocalDateTime datum) {
        this.id = id;
        this.herzfrequenz = herzfrequenz;
        this.atemfrequenz = atemfrequenz;
        this.systolisch = systolisch;
        this.diastolisch = diastolisch;
        this.temperatur = temperatur;
        this.datum = datum;
    }

    public UUID getId() {
        return id;
    }

    public short getHerzfrequenz() {
        return herzfrequenz;
    }

    public byte getAtemfrequenz() {
        return atemfrequenz;
    }

    public short getSystolisch() {
        return systolisch;
    }

    public short getDiastolisch() {
        return diastolisch;
    }

    public float getTemperatur() {
        return temperatur;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

}