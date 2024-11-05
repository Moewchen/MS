package com.bht.MediTrack.Repositories;

import com.bht.MediTrack.Entities.Arzt;
import org.springframework.data.repository.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArztRepository extends Repository<Arzt, Long>{
    Optional<Arzt> findArztById(UUID id);
    List<Arzt> findArztByName(String fristname, String lastname);
    List<Arzt> findArztByFachrichtung(String fachrichtung);
}
