package com.bht.meditrack.Vitaldatenmanagement.ui.controller;

import com.bht.meditrack.Patientenverwaltung.application.services.PatientService;
import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.Vitaldatenmanagement.application.services.VitaldatenService;
import com.bht.meditrack.Vitaldatenmanagement.domain.model.Vitaldaten;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vitaldaten") // endpoint mapping name
public class VitaldatenController {

    private final VitaldatenService vitaldatenService;
    private final PatientService patientService;

    @Autowired
    public VitaldatenController(VitaldatenService vitaldatenService, PatientService patientService) {
        this.vitaldatenService = vitaldatenService;
        this.patientService = patientService;
    }

    @PatchMapping(path = "/upsert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vitaldaten upsertVitaldaten(@RequestParam final UUID patientId, @RequestBody final Vitaldaten vitaldaten) {
        return vitaldatenService.upsertVitaldaten(patientId, vitaldaten);
    }

    @PostMapping(path = "/patient/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vitaldaten> createVitaldaten(@PathVariable final UUID patientId, @RequestBody Vitaldaten vitaldaten) {
        Optional<Patient> patientOptional = patientService.findById(patientId);
        if (patientOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        vitaldaten.setPatient(patientOptional.get());
        Vitaldaten createdVitaldaten = vitaldatenService.upsertVitaldaten(patientId, vitaldaten);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVitaldaten);
    }

    @GetMapping(path = "/patient/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vitaldaten>> getVitaldatenByPatientId(@PathVariable UUID patientId) {
        List<Vitaldaten> vitaldatenList = vitaldatenService.findByPatientId(patientId);
        if (vitaldatenList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vitaldatenList);
    }
}