package com.bht.meditrack.vitaldatenmanagement.infrastructure.persistence;

import com.bht.meditrack.patientenverwaltung.infrastructure.persistence.PatientEntity;
import com.bht.meditrack.vitaldatenmanagement.domain.model.Vitaldaten;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VitaldatenMapper {
    private VitaldatenMapper() {
    }

    public static VitaldatenEntity toVitaldatenEntity(UUID patientId, Vitaldaten vitaldaten) {
        VitaldatenEntity entity = new VitaldatenEntity();
        entity.setId(vitaldaten.getId());
        entity.setHerzfrequenz(vitaldaten.getHerzfrequenz());
        entity.setAtemfrequenz(vitaldaten.getAtemfrequenz());
        entity.setSystolisch(vitaldaten.getSystolisch());
        entity.setDiastolisch(vitaldaten.getDiastolisch());
        entity.setTemperatur(vitaldaten.getTemperatur());
        entity.setDatum(vitaldaten.getDatum());

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientId);
        entity.setPatient(patientEntity);

        return entity;
    }

    public static Vitaldaten toVitaldatenDomain(VitaldatenEntity entity) {
        Vitaldaten vitaldaten = new Vitaldaten();
        vitaldaten.setId(entity.getId());
        vitaldaten.setHerzfrequenz(entity.getHerzfrequenz());
        vitaldaten.setAtemfrequenz(entity.getAtemfrequenz());
        vitaldaten.setSystolisch(entity.getSystolisch());
        vitaldaten.setDiastolisch(entity.getDiastolisch());
        vitaldaten.setTemperatur(entity.getTemperatur());
        vitaldaten.setDatum(entity.getDatum());

        return vitaldaten;
    }

    public static List<Vitaldaten> toVitaldatenDomainList(List<VitaldatenEntity> entityList) {
        return entityList.stream()
                .map(VitaldatenMapper::toVitaldatenDomain)
                .collect(Collectors.toList());
    }

}