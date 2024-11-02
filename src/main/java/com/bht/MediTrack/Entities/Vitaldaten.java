package com.bht.MediTrack.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
@Table(name = "vitaldaten")
public class Vitaldaten {

    @Id
    private String id;
    private byte herzfrequenz;
    private byte atemfrequenz;
    private byte systolisch;
    private byte diastolisch;
    private byte temperatur;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;

    public Vitaldaten() {}

    public Vitaldaten(String id, byte herzfrequenz, byte atemfrequenz, byte systolisch, byte diastolisch, byte temperatur, Date datum) {
        this.id = id;
        this.herzfrequenz = herzfrequenz;
        this.atemfrequenz = atemfrequenz;
        this.systolisch = systolisch;
        this.diastolisch = diastolisch;
        this.temperatur = temperatur;
        this.datum = datum;

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public byte getHerzfrequenz() {
        return herzfrequenz;
    }
    public void setHerzfrequenz(byte herzfrequenz) {
        this.herzfrequenz = herzfrequenz;
    }
    public byte getAtemfrequenz() {
        return atemfrequenz;
    }
    public void setAtemfrequenz(byte atemfrequenz) {
        this.atemfrequenz = atemfrequenz;
    }
    public byte getSystolisch() {
        return systolisch;
    }
    public void setSystolisch(byte systolisch) {
        this.systolisch = systolisch;
    }
    public byte getDiastolisch() {
        return diastolisch;
    }
    public void setDiastolisch(byte diastolisch) {
        this.diastolisch = diastolisch;
    }
    public byte getTemperatur() {
        return temperatur;
    }
    public void setTemperatur(byte temperatur) {
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
