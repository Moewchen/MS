package com.bht.MediTrack.Behandlungsmanagement.infrastructure.repositories;

import com.bht.MediTrack.Behandlungsmanagement.domain.model.Behandlung;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BehandlungRepository {

    private final Map<UUID, Behandlung> behandlungMap = new HashMap<>();

    //Methode, um eine neue Behandlung zu erstellen
    public Behandlung createBehandlung(Behandlung behandlung) {
        behandlung.setId(UUID.randomUUID());
        behandlungMap.put(behandlung.getId(), behandlung);
        return behandlung;
    }

    //Methode, um eine Behandlung zu speichern (aktualisiert oder fügt hinzu)
    public Behandlung save(Behandlung behandlung) {
        behandlungMap.put(behandlung.getId(), behandlung);
        return behandlung;
    }

    //Methode, um eine bestehende Behandlung basierend auf der ID abzurufen
    public Optional<Behandlung> findById(UUID id) {
        return Optional.ofNullable(behandlungMap.get(id));
    }

    // Methode, um eine bestehende Behandlung zu aktualisieren
    public void updateBehandlung(String beschreibung, UUID id) {
        Behandlung behandlung = behandlungMap.get(id);
        if (behandlung != null) {
            behandlung.setBeschreibung(beschreibung);
            behandlungMap.put(id, behandlung);
        } else {
            throw new IllegalArgumentException("Behandlung mit der ID " + id + " nicht gefunden.");
        }
    }

    //Methode alle Behandlungen eines Patienten durch dessen Id zu finden
    public List<Behandlung> getBehandlungenByPatientId(UUID patientId) {
        List<Behandlung> patientBehandlungen = new ArrayList<>();
        for (Behandlung behandlung : behandlungMap.values()) {
            if (behandlung.getPatientId().equals(patientId)) {
                patientBehandlungen.add(behandlung);
            }
        }
        return patientBehandlungen;
    }
}
