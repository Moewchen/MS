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
import java.util.function.Function;

@RestController
@RequestMapping("/vitaldaten") // endpoint mapping name
public record VitaldatenController(VitaldatenService vitaldatenService, PatientService patientService) {


    // Funktionale Hilfsmethoden
    private static Function<Vitaldaten, Optional<Vitaldaten>> upsertVitaldatenFunction(
            VitaldatenService service, UUID patientId) {
        return vitaldaten -> service.upsertVitaldaten(patientId, vitaldaten);
    }

    private static Function<Optional<Patient>, ResponseEntity<Optional<Vitaldaten>>> createVitaldatenFunction(
            VitaldatenService service, UUID patientId, Vitaldaten vitaldaten) {
        return patientOpt -> patientOpt
                .map(patient -> {
                    // Statt Copy-Konstruktor verwenden wir die bestehende Vitaldaten-Instanz
                    vitaldaten.setPatient(patient);
                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(service.upsertVitaldaten(patientId, vitaldaten));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private static Function<List<Vitaldaten>, ResponseEntity<List<Vitaldaten>>> toResponseEntity() {
        return vitaldatenList -> vitaldatenList.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(vitaldatenList);
    }

    // Controller-Endpunkte
    @PatchMapping(path = "/upsert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Vitaldaten> upsertVitaldaten(
            @RequestParam final UUID patientId,
            @RequestBody final Vitaldaten vitaldaten) {
        return upsertVitaldatenFunction(vitaldatenService, patientId).apply(vitaldaten);
    }

    @PostMapping(path = "/patient/{patientId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Vitaldaten>> createVitaldaten(
            @PathVariable final UUID patientId,
            @RequestBody final Vitaldaten vitaldaten) {
        return createVitaldatenFunction(vitaldatenService, patientId, vitaldaten)
                .apply(patientService.findById(patientId));
    }

    @GetMapping(path = "/patient/{patientId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vitaldaten>> getVitaldatenByPatientId(
            @PathVariable UUID patientId) {
        return toResponseEntity().apply(vitaldatenService.findByPatientId(patientId));
    }
}