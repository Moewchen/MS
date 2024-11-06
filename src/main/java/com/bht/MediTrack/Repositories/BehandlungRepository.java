package com.bht.MediTrack.Repositories;

import com.bht.MediTrack.Entities.Behandlung;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BehandlungRepository {

    private final Map<UUID, Behandlung> behandlungMap = new HashMap<>();

    //Methode, um eine neue Behandlung zu erstellen
    public Behandlung createBehandlung(Behandlung behandlung) {
        behandlung.setId(UUID.randomUUID()); // Erzeugt eine UUID, wenn noch keine gesetzt ist
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

    //Methode um die Behandlung eines Patienten durch dessen Id zu finden
    public Behandlung getBehandlungenByPatientenId(UUID patientId) {
        return behandlungMap.get(patientId);
    }
}
