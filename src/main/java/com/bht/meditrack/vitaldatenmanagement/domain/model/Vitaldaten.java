package com.bht.meditrack.vitaldatenmanagement.domain.model;

import com.bht.meditrack.patientenverwaltung.domain.model.Patient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Vitaldaten {

    private UUID id;
    private Patient patient;
    private short herzfrequenz;
    private byte atemfrequenz;
    private short systolisch;
    private short diastolisch;
    private float temperatur;
    private LocalDateTime datum;

    public Vitaldaten(UUID id, short herzfrequenz, byte atemfrequenz, short systolisch, short diastolisch, float temperatur, LocalDateTime datum) {
        this.id = id;
        this.herzfrequenz = herzfrequenz;
        this.atemfrequenz = atemfrequenz;
        this.systolisch = systolisch;
        this.diastolisch = diastolisch;
        this.temperatur = temperatur;
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