package com.bht.MediTrack.Vitaldatenmanagement.infrastructure.repositories;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/*
public interface VitaldatenRepository extends CrudRepository<Vitaldaten, UUID> {
}
*/
public interface VitaldatenRepository extends JpaRepository<Vitaldaten,UUID> {
    List<Vitaldaten> findByPatientId(UUID patientId);
}
/*
@Repository
public class VitaldatenRepository {
    //TODO: Implementierung database anbinden
    private final Map<UUID, Vitaldaten> database = new HashMap<>();

    public Optional<Vitaldaten> getVitaldatenByPatientenId(UUID patientId){

        return Optional.ofNullable(database.get(patientId));
    }
    public Optional<Vitaldaten> getVitaldatenById(UUID Id){
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
*/
