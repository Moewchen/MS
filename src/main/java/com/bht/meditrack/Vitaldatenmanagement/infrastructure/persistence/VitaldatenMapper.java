package com.bht.meditrack.Vitaldatenmanagement.infrastructure.persistence;

import com.bht.meditrack.Vitaldatenmanagement.domain.model.Vitaldaten;

import java.util.List;

public class VitaldatenMapper {
    public static VitaldatenEntity toVitaldatenEntity(Vitaldaten vitaldaten) {
        VitaldatenEntity entity = new VitaldatenEntity();
        entity.setId(vitaldaten.getId());
        entity.setHerzfrequenz(vitaldaten.getHerzfrequenz());
        return entity;
    }

    public static Vitaldaten toVitaldatenDomain(VitaldatenEntity entity) {
        Vitaldaten vitaldaten = new Vitaldaten();
        vitaldaten.setId(entity.getId());
        vitaldaten.setHerzfrequenz(entity.getHerzfrequenz());
        return vitaldaten;
    }

    public static List<Vitaldaten> toVitaldatenDomainList(List<VitaldatenEntity> entities) {
        return entities.stream().map(VitaldatenMapper::toVitaldatenDomain).toList();
    }
}
