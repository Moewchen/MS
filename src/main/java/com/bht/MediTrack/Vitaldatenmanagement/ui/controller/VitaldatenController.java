package com.bht.MediTrack.Vitaldatenmanagement.ui.controller;

import com.bht.MediTrack.ApplicationKonstante;
import com.bht.MediTrack.Vitaldatenmanagement.application.services.VitaldatenService;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;


@RestController
//@RequestMapping(ApplicationKonstante.API_VERSION+"/vitaldaten") // endpoint mapping name
@RequestMapping("/vitaldaten") // endpoint mapping name
public class VitaldatenController {

    @Autowired
    private VitaldatenService vitaldatenService;

    @Autowired
    public VitaldatenController(VitaldatenService vitaldatenService) {
        this.vitaldatenService = vitaldatenService;
    }

    /*
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
    */

    @PatchMapping(path = "/upsert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public Vitaldaten upsertVitaldaten ( @RequestParam final UUID patientId,
        @RequestBody final Vitaldaten vitaldaten){
            Vitaldaten savedVitaldaten = vitaldatenService.upsertVitaldaten(patientId, vitaldaten);
            return savedVitaldaten;
        }

    @PostMapping(path = "/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Vitaldaten> createVitaldaten (@PathVariable final UUID patientId,
        @RequestBody Vitaldaten vitaldaten){
            Vitaldaten createdVitaldaten = vitaldatenService.upsertVitaldaten(patientId, vitaldaten);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVitaldaten);
        }
}