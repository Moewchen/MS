package com.bht.MediTrack.Repositories;

import com.bht.MediTrack.Entities.Vitaldaten;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class VitaldatenRepository {

    private final Map<UUID, Vitaldaten> database = new HashMap<>();
    //TODO: das untere wieder rausnehmen
    //UUID patientId = UUID.randomUUID();
    public Optional<Vitaldaten> getVitaldatenByPatientenId(UUID patientId){
        //TODO: patientId mit der Klasse verbinden
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
    public Vitaldaten createVitaldaten(Vitaldaten vitaldaten){
        database.put(vitaldaten.getId(), vitaldaten);
        return vitaldaten;

    }


}
