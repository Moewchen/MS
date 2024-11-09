package com.bht.MediTrack.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "vitaldaten")
public class Vitaldaten {

    @Id
    private UUID id;
    private short herzfrequenz;
    private byte atemfrequenz;
    private short systolisch;
    private short diastolisch;
    private float temperatur;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;

    public Vitaldaten() {}

    public Vitaldaten(UUID id, short herzfrequenz, byte atemfrequenz, short systolisch, short diastolisch, float temperatur, Date datum) {
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
    public void setId(UUID id) {
        this.id = id;
    }
    public short getHerzfrequenz() {
        return herzfrequenz;
    }
    public void setHerzfrequenz(short herzfrequenz) {
        this.herzfrequenz = herzfrequenz;
    }
    public byte getAtemfrequenz() {
        return atemfrequenz;
    }
    public void setAtemfrequenz(byte atemfrequenz) {
        this.atemfrequenz = atemfrequenz;
    }
    public short getSystolisch() {
        return systolisch;
    }
    public void setSystolisch(short systolisch) {
        this.systolisch = systolisch;
    }
    public short getDiastolisch() {
        return diastolisch;
    }
    public void setDiastolisch(short diastolisch) {
        this.diastolisch = diastolisch;
    }
    public float getTemperatur() {
        return temperatur;
    }
    public void setTemperatur(float temperatur) {
        this.temperatur = temperatur;
    }
    public Date getDatum() {
        return datum;
    }
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "Vitaldaten{" +
                "id='" + id + '\'' +
                ", herzfrequenz=" + herzfrequenz +
                ", atemfrequenz=" + atemfrequenz +
                ", systolisch=" + systolisch +
                ", diastolisch=" + diastolisch +
                ", temperatur=" + temperatur +
                ", datum=" + datum +'}';
    }
}