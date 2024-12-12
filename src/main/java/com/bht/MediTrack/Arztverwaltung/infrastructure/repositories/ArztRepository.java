package com.bht.MediTrack.Arztverwaltung.infrastructure.repositories;

import com.bht.MediTrack.Arztverwaltung.domain.model.Arzt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ArztRepository extends JpaRepository<Arzt, UUID> {
}
