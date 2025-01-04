package com.bht.MediTrack.Vitaldatenmanagement.ui.controller;

import com.bht.MediTrack.ApplicationKonstante;
import com.bht.MediTrack.Patientenverwaltung.application.services.PatientService;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;
import com.bht.MediTrack.Vitaldatenmanagement.application.services.VitaldatenService;
import com.bht.MediTrack.Vitaldatenmanagement.domain.model.Vitaldaten;
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

    @Autowired
    private VitaldatenService vitaldatenService;
    @Autowired
    private PatientService patientService;

    @Autowired
    public VitaldatenController(VitaldatenService vitaldatenService) {
        this.vitaldatenService = vitaldatenService;
    }

    @PatchMapping(path = "/upsert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public Vitaldaten upsertVitaldaten ( @RequestParam final UUID patientId,
        @RequestBody final Vitaldaten vitaldaten){
            Vitaldaten savedVitaldaten = vitaldatenService.upsertVitaldaten(patientId, vitaldaten);
            return savedVitaldaten;
        }

    @PostMapping(path = "/patient/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Vitaldaten> createVitaldaten (@PathVariable final UUID patientId,
        @RequestBody Vitaldaten vitaldaten){
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