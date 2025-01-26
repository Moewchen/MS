package com.bht.meditrack.arztverwaltung.infrastructure.repositories;

import com.bht.meditrack.arztverwaltung.domain.model.Arzt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ArztRepository extends JpaRepository<Arzt, UUID> {
}
