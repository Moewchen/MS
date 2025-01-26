package com.bht.meditrack.Patientenverwaltung.ui.controller;

import com.bht.meditrack.Patientenverwaltung.application.services.PatientService;
import com.bht.meditrack.Patientenverwaltung.domain.model.Patient;
import com.bht.meditrack.shared.SecurityConfig;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;


    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Patient>> createPatient(@RequestBody Patient patient) {
        return Optional.ofNullable(patient)
                .map(p -> {
                    if (p.getId() == null) {
                        p.setId(UUID.randomUUID());
                    }
                    return p;
                })
                .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(patientService.upsertPatient(p.getId(), p)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PatchMapping(path = "/upsert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Patient>> updatePatient(
            @RequestParam final UUID patientId,
            @RequestBody final Patient patient) {
        return Optional.ofNullable(patientId)
                .flatMap(id -> Optional.ofNullable(patient)
                        .map(p -> ResponseEntity.ok(patientService.upsertPatient(id, p))))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> getAllPatients() {
        return Optional.of(patientService.getAllPatients())
                .map(patients -> patients.stream()
                        .map(patient -> {
                            patient.setVitaldaten(null);
                            return patient;
                        })
                        .toList())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(path = "/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable UUID patientId) {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(auth -> (Jwt) auth.getPrincipal())
                .flatMap(jwt -> validateAndGetPatient(jwt, patientId))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    private Optional<ResponseEntity<Patient>> validateAndGetPatient(Jwt jwt, UUID patientId) {
        return Optional.of(jwt)
                .filter(token -> Optional.ofNullable(token.getClaim("preferred_username")).isPresent())
                .filter(token -> !isRestrictedAccess(token, patientId))
                .flatMap(token -> patientService.findById(patientId))
                .map(ResponseEntity::ok)
                .or(() -> Optional.of(ResponseEntity.status(HttpStatus.FORBIDDEN).build()));
    }

    private boolean isRestrictedAccess(Jwt jwt, UUID patientId) {
        return jwt.getClaim("preferred_username").equals(SecurityConfig.ROLE_USER_PATIENT) &&
                !patientId.toString().equals(jwt.getClaim("patientId"));
    }

    @GetMapping(path = "/krankenkasse/{krankenkasse}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> getPatientsByKrankenkasse(@PathVariable String krankenkasse) {
        List<Patient> patients = patientService.findByKrankenkasse(krankenkasse);
        return patients.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(patients);
    }
}
