package com.bht.MediTrack.Behandlungsmanagement.ui;

import com.bht.MediTrack.Behandlungsmanagement.application.services.Behandlungsmanagementservice;
import com.bht.MediTrack.Behandlungsmanagement.domain.model.Behandlung;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/behandlungen")
public class BehandlungController {

    private final Behandlungsmanagementservice behandlungsmanagementservice;

    public BehandlungController(Behandlungsmanagementservice behandlungsmanagementservice) {
        this.behandlungsmanagementservice = behandlungsmanagementservice;
    }


    @PostMapping
    public ResponseEntity<Behandlung> createBehandlung(@RequestBody Behandlung behandlung) {
        Behandlung createdBehandlung = behandlungsmanagementservice.createBehandlung(behandlung);
        return ResponseEntity.ok(createdBehandlung);
    }


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Behandlung>> getBehandlungenByPatientId(@PathVariable UUID patientId) {
        List<Behandlung> behandlungen = behandlungsmanagementservice.getBehandlungenByPatientId(patientId);
        return ResponseEntity.ok(behandlungen);
    }

/*
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBehandlung(@PathVariable UUID id, @RequestParam String beschreibung) {
        behandlungsmanagementservice.updateBehandlung(beschreibung, id);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBehandlung(@PathVariable UUID id) {
        behandlungsmanagementservice.deleteBehandlung(id);
        return ResponseEntity.ok().build();
    }

 */
}