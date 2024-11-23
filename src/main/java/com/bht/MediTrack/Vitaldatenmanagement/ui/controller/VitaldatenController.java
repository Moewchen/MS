package com.bht.MediTrack.Vitaldatenmanagement.ui.controller;

import com.bht.MediTrack.ApplicationKonstante;
import com.bht.MediTrack.Vitaldatenmanagement.application.services.VitaldatenService;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(ApplicationKonstante.API_VERSION+"/vitaldaten")
public class VitaldatenController {

    private final VitaldatenService vitaldatenService;

    @Autowired
    public VitaldatenController(VitaldatenService vitaldatenService) {
        this.vitaldatenService = vitaldatenService;
    }

    @PostMapping
    public ResponseEntity<Vitaldaten> createVitaldaten(@RequestParam UUID patientId, @RequestBody Vitaldaten vitaldaten) {
        Vitaldaten createdVitaldaten = vitaldatenService.createVitaldaten(patientId, vitaldaten);
        return ResponseEntity.ok(createdVitaldaten);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vitaldaten> getVitaldatenById(@PathVariable UUID id) {
        Optional<Vitaldaten> vitaldaten = vitaldatenService.getVitaldatenById(id);
        return vitaldaten.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Vitaldaten> getVitaldatenByPatientenId(@PathVariable UUID patientId) {
        Optional<Vitaldaten> vitaldaten = vitaldatenService.getVitaldatenByPatientenId(patientId);
        return vitaldaten.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}