package com.bht.meditrack.Vitaldatenmanagement.domain.model;

import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;
import java.util.UUID;

@EntityScan
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vitaldaten")
public class Vitaldaten {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private short herzfrequenz;
    private byte atemfrequenz;
    private short systolisch;
    private short diastolisch;
    private float temperatur;

    @Column(columnDefinition = "TIMESTAMP")
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