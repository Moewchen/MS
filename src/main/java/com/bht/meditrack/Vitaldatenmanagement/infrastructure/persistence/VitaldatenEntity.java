package com.bht.meditrack.Vitaldatenmanagement.infrastructure.persistence;

import com.bht.meditrack.Patientenverwaltung.infrastructure.persistence.PatientEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vitaldaten")
@Getter
@Setter
public class VitaldatenEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    private short herzfrequenz;
    private byte atemfrequenz;
    private short systolisch;
    private short diastolisch;
    private float temperatur;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime datum;

}
