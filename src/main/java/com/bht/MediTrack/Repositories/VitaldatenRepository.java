package com.bht.MediTrack.Repositories;

import com.bht.MediTrack.Entities.Vitaldaten;
import jakarta.persistence.Id;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class VitaldatenRepository {
    //TODO: Implementierung database anbinden
    private final Map<UUID, Vitaldaten> database = new HashMap<>();

    public Optional<Vitaldaten> getVitaldatenByPatientenId(UUID patientId){

        return Optional.ofNullable(database.get(patientId));
    }
    public Optional<Vitaldaten> getVitaldatenByID(UUID Id){
        return Optional.ofNullable(database.get(Id));

    }
    public boolean updateVitaldaten(UUID Id, Vitaldaten vitaldaten){
        if (database.containsKey(Id)){
            database.put(Id, vitaldaten);
            return true;
        }
        return false;

    }
    public Vitaldaten createVitaldaten(UUID Id, Vitaldaten vitaldaten){
        database.put(vitaldaten.getId(), vitaldaten);
        return vitaldaten;

    }
    public Vitaldaten deleteVitaldaten(UUID Id, Vitaldaten vitaldaten){
        return database.remove(vitaldaten.getId());
    }

}