package com.bht.MediTrack.Patientenverwaltung.ui.controller;

import com.bht.MediTrack.Patientenverwaltung.application.services.PatientService;
import com.bht.MediTrack.Patientenverwaltung.domain.model.Patient;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PatchMapping(path = "/upsert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient upsertPatient(@RequestParam final UUID patientId, @RequestBody final Patient patient) {
        return patientService.upsertPatient(patientId, patient);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Patient> upsertPatient(@RequestBody Patient patient) {
        UUID patientId = patient.getId() != null ? patient.getId() : UUID.randomUUID();
        Patient upsertedPatient = patientService.upsertPatient(patientId, patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(upsertedPatient);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        // Exclude vitaldaten from the response
        patients.forEach(patient -> patient.setVitaldaten(null));
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable UUID patientId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userPatientId = jwt.getClaim("patientId");
        String userRole = jwt.getClaim("role");

        if (userRole.equals("user_patient") && !patientId.toString().equals(userPatientId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Optional<Patient> patient = patientService.findById(patientId);
        return patient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    }


