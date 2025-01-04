package com.bht.meditrack.Behandlungsmanagement.ui;

import com.bht.meditrack.Behandlungsmanagement.application.services.Behandlungsmanagementservice;
import com.bht.meditrack.Behandlungsmanagement.domain.model.Behandlung;
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

}