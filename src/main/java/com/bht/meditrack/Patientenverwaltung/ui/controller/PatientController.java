package com.bht.meditrack.Patientenverwaltung.ui.controller;

import com.bht.meditrack.Patientenverwaltung.application.services.PatientService;
import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.shared.SecurityConfig;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private PatientService patientService;
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);


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

    @GetMapping(path = "/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> getPatientById(@PathVariable UUID patientId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        logger.info("jwt: " + jwt);
        System.out.println("jwt: " + jwt);
        System.out.println("jwt.getTokenValue()" + jwt.getTokenValue());

        String userPatientId = jwt.getClaim("patientId");
        String userRole = jwt.getClaim("preferred_username");

        if (userRole == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (userRole.equals(SecurityConfig.ROLE_USER_PATIENT) && !patientId.toString().equals(userPatientId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


        Optional<Patient> patient = patientService.findById(patientId);
        return patient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}